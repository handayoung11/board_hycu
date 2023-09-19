package hycu.board.token;

import hycu.board.utils.FormatterUtils;
import hycu.board.utils.RandomUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID value;
    private LocalDateTime expiryDate;
    @Column(columnDefinition = "char")
    private String jti;

    public static RefreshToken createToken() {
        RefreshToken token = new RefreshToken();
        token.generate();
        return token;
    }

    public void generate() {
        LocalDateTime now = LocalDateTime.now();
        value = UUID.randomUUID();
        expiryDate = now.plusSeconds(TokenExpiry.REFRESH_EXPIRY_SEC);
        jti = RandomUtils.randomAlphaNumeric(2) + "-" + FormatterUtils.REFRESH_TOKEN_JTI_FORMATTER.format(now);
    }

    public boolean isExpired() {
        return expiryDate.isBefore(LocalDateTime.now());
    }
}
