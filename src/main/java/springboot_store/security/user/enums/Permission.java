package springboot_store.security.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    STORE_CREATE("STORE:CREATE"),
    STORE_READ("STORE:READ"),
    STORE_UPDATE("STORE:UPDATE"),
    STORE_DELETE("STORE:DELETE"),
    CLIENT_CREATE("CLIENT:CREATE"),
    CLIENT_READ("CLIENT:READ"),
    CLIENT_UPDATE("CLIENT:UPDATE"),
    CLIENT_DELETE("CLIENT:DELETE");

    private final String permissions;
}