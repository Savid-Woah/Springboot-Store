package springboot_store.security.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    ADMIN_CREATE("ADMIN:CREATE"),
    ADMIN_READ("ADMIN:READ"),
    ADMIN_UPDATE("ADMIN:UPDATE"),
    ADMIN_DELETE("ADMIN:DELETE");

    private final String permissions;
}