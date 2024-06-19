package springboot_store.security.auth.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import springboot_store.exception.BackendException;
import springboot_store.security.auth.request.LoginRequest;
import springboot_store.security.auth.request.RegisterStoreRequest;
import springboot_store.security.service.JwtService;
import springboot_store.security.user.model.User;
import springboot_store.security.user.request.UserRequest;
import springboot_store.security.user.service.UserService;
import springboot_store.store.request.StoreRequest;
import springboot_store.store.service.StoreService;

import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static springboot_store.exception.MsgCode.*;
import static springboot_store.response.handler.ResponseHandler.generateResponse;
import static springboot_store.response.response.ResponseMessage.STORE_REGISTERED;
import static springboot_store.security.user.enums.Role.STORE;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final StoreService storeService;
    private final AuthenticationManager authenticationManager;

    public String login(LoginRequest loginRequest) {

        try {

            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();
            User user = userService.getUserByEmail(email).orElseThrow(() -> new BackendException(USER_NOT_FOUND));
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
            authenticationManager.authenticate(authToken);
            String jwt = jwtService.generateToken(user);
            jwtService.saveUserToken(user, jwt);
            return jwt;

        } catch (AuthenticationException exception) {
            throw new BackendException(INVALID_CREDENTIALS);
        }
    }

    public Map<String, Object> registerStore(RegisterStoreRequest registerStoreRequest) {

        UserRequest userRequest = registerStoreRequest.getUserRequest();
        StoreRequest storeRequest = registerStoreRequest.getStoreRequest();
        User user = userService.createUser(userRequest, STORE);
        storeService.createStore(storeRequest, user);

        return generateResponse(null, CREATED, STORE_REGISTERED);
    }
}