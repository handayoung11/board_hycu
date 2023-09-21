package hycu.board.token;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static hycu.board.token.TokenExpiry.REFRESH_EXPIRY_SEC;
import static hycu.board.utils.CookieUtils.makeSecuredCookie;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenSvc tokenSvc;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void token(Authentication auth, HttpServletResponse resp) {
        RefreshToken refreshToken = tokenSvc.generateRefreshToken();
        String token = tokenSvc.generateToken(auth, refreshToken);
        configTokenCookies(token, refreshToken.getValue().toString(), resp);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void checkToken() {}

    @PostMapping("/refresh")
    public void reIssueToken(@CookieValue("refreshToken") Cookie refreshToken,
                             @CookieValue("token") Cookie accessToken,
                             HttpServletResponse resp) {
        String[] tokens = tokenSvc.reIssueToken(refreshToken.getValue(), accessToken.getValue());
        configTokenCookies(tokens[0], tokens[1], resp);
    }

    private static void configTokenCookies(String token, String refreshToken, HttpServletResponse resp) {
        Cookie cookie = makeSecuredCookie("token", token, REFRESH_EXPIRY_SEC);
        cookie.setHttpOnly(false);
        resp.addCookie(cookie);
        resp.addCookie(makeSecuredCookie("refreshToken", refreshToken, REFRESH_EXPIRY_SEC));
    }
}