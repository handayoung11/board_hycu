package hycu.board.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class BearerAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException {
        //TODO origin port yml 설정 처리
        if (ex.getMessage().contains("expired")) {
            response.getWriter().write("EXP");
        }

        String origin = request.getScheme() + "://" + request.getServerName() + ":3000";
        response.setStatus(401);
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.addHeader("Access-Control-Allow-Credentials", "true");
    }
}

