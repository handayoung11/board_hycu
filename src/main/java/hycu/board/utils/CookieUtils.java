package hycu.board.utils;

import jakarta.servlet.http.Cookie;

public class CookieUtils {
    private static boolean secured = true;

    public static Cookie makeSecuredCookie(String key, String value, int expiry) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(expiry);
        cookie.setSecure(secured);
        cookie.setPath("/");
        cookie.setAttribute("SameSite", "none");
        return cookie;
    }
}
