package springboot_store.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageTextResolver messageTextResolver;

    @ExceptionHandler(BackendException.class)
    public ResponseEntity<Error> backendExceptionHandler(BackendException ex) {

        MsgCode msgCode = ex.getMsgCode();
        Integer code = msgCode.getCode();

        Error error = Error
                .builder()
                .code(code)
                .message(messageTextResolver.getMessage(msgCode))
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {

        Map<String, Object> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {

            errorMap.put(error.getField(), error.getDefaultMessage());

        });

        return errorMap;
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        System.out.println("Hola llegué");
        return new ResponseEntity<>("Access Denied: You do not have the necessary permissions to access this resource.", HttpStatus.FORBIDDEN);
    }
}