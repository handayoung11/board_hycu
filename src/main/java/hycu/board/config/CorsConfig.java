package hycu.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //TODO origin 변수 설정
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:1234")
                .allowedMethods("*")
                .allowCredentials(true);
    }
}
