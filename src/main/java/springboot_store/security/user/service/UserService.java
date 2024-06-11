package springboot_store.security.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot_store.security.user.enums.Role;
import springboot_store.security.user.model.User;
import springboot_store.security.user.repository.UserRepository;
import springboot_store.security.user.request.UserRequest;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(UserRequest userRequest, Role role) {

        User user = new User(userRequest, role);
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }
}