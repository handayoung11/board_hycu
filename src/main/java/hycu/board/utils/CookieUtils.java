package hycu.board.utils;

import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

public class CookieUtils {

    @Value("${cookie.secured}")
    private static boolean secured;

    public static Cookie makeSecuredCookie(String key, String value, int expiry) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(expiry);
        cookie.setSecure(secured);
        cookie.setPath("/");
        return cookie;
    }
}
