package springboot_store.response.handler;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static Map<String, Object> generateResponse(Object data, HttpStatus status, String message) {

        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("status", status);
        response.put("message", message);
        return response;
    }

    public static Map<String, Object> generateResponse(HttpStatus status, String message) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        return response;
    }
}