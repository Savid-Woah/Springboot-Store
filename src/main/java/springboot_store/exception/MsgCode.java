package springboot_store.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MsgCode {

    OOPS_ERROR(500, "oops-error"),
    INVALID_CREDENTIALS(401, "invalid-credentials"),
    UNAUTHORIZED(403, "unauthorized"),
    USER_NOT_FOUND(404, "user-not-found"),
    USER_ALREADY_EXISTS(409, "user-already-exists"),
    PRODUCT_NOT_FOUND(404, "product-not-found"),
    STORE_NOT_FOUND(404, "store-not-found")

    ;

    private final Integer code;
    private final String languageKey;
}