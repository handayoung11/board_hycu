package hycu.board.token;

import hycu.board.ex.LogoutErrRes;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.stream.Collectors;

import static hycu.board.utils.ExUtils.makeHttpClientErrorEx;

@Service
public class TokenSvc {
    private final JwtEncoder encoder;
    private final RefreshTokenRepo refreshTokenRepo;
    private final NimbusJwtDecoder jwtDecoder;
    private final JwtTimestampValidator jwtValidator = new JwtTimestampValidator();

    public TokenSvc(JwtEncoder encoder, RefreshTokenRepo refreshTokenRepo, @Value("${jwt.public.key}") RSAPublicKey key) {
        this.encoder = encoder;
        this.refreshTokenRepo = refreshTokenRepo;
        this.jwtDecoder = NimbusJwtDecoder.withPublicKey(key).build();
        jwtDecoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>());
    }


    public RefreshToken generateRefreshToken() {
        RefreshToken token = RefreshToken.createToken();
        refreshTokenRepo.save(token);
        return token;
    }

    public String generateToken(Authentication auth, RefreshToken token) {
        String roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        return generateToken(roles, auth.getName(), token);
    }

    private String generateToken(String roles, String userId, RefreshToken token) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .expiresAt(now.plusSeconds(TokenExpiry.JWT_EXPIRY_SEC))
                .id(token.getId() + token.getJti())
                .subject(userId)
                .claim("rls", roles)
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Transactional
    public String[] reIssueToken(String refreshTokenVal, String accessToken) {
        Jwt jwt;
        try {
            jwt = jwtDecoder.decode(accessToken);
        } catch (JwtException e) {
            throw makeHttpClientErrorEx(HttpStatus.UNAUTHORIZED, new LogoutErrRes("jwt is not valid"));
        }

        if (!jwtValidator.validate(jwt).hasErrors()) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "jwt is valid");
        }

        String jti = jwt.getClaimAsString("jti");
        RefreshToken refreshToken = refreshTokenRepo.findByValueAndJti(refreshTokenVal, jti)
                .orElseThrow(() -> makeHttpClientErrorEx(HttpStatus.UNAUTHORIZED, new LogoutErrRes("refreshToken is not valid")));
        if (refreshToken.isExpired()) {
            throw makeHttpClientErrorEx(HttpStatus.UNAUTHORIZED, new LogoutErrRes("refreshtoken is expired"));
        }

        refreshToken.generate();
        String token = generateToken(jwt.getClaimAsString("rls"), jwt.getSubject(), refreshToken);
        return new String[] {token, refreshToken.getValueStr()};
    }
    }
}
