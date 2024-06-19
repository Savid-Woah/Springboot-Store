package springboot_store.security.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static springboot_store.security.constant.AuthExceptionMessages.ACCESS_DENIED;

@Component
public class AuthAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(

            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException

    ) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(ACCESS_DENIED);
    }
}
