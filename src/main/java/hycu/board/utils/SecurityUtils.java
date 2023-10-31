package hycu.board.utils;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.HttpClientErrorException;

public class SecurityUtils {

    public static Long getId() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            return Long.parseLong(name);
        } catch (NumberFormatException e) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "죄송합니다. 토큰이 잘못되었습니다. 로그아웃 후 다시 이용해주세요.");
        }
    }
}
