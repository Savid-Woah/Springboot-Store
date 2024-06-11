package springboot_store.security.auth.api;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot_store.security.auth.request.LoginRequest;
import springboot_store.security.auth.request.RegisterStoreRequest;
import springboot_store.security.auth.service.AuthService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("store/api/v1/auth/")
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "login")
    public String login(@Validated @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping(path = "register-store")
    public Map<String, Object> registerStore(@Validated @RequestBody RegisterStoreRequest registerStoreRequest) {
        return authService.registerStore(registerStoreRequest);
    }
}