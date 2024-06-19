package springboot_store.security.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import springboot_store.security.user.enums.Permission;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static springboot_store.security.user.enums.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {

    STORE(
            Set.of(
                    STORE_CREATE,
                    STORE_READ,
                    STORE_UPDATE,
                    STORE_DELETE
            )
    ),

    CLIENT(
            Set.of(
                CLIENT_CREATE,
                CLIENT_READ,
                CLIENT_UPDATE,
                CLIENT_DELETE
            )
    );

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissions()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }
}