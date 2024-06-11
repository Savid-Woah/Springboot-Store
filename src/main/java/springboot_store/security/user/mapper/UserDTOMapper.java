package springboot_store.security.user.mapper;

import org.springframework.stereotype.Component;
import springboot_store.security.user.dto.UserDTO;
import springboot_store.security.user.model.User;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getEmail(),
                user.getRole()
        );
    }
}