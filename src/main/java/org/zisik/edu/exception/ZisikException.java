package org.zisik.edu.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class ZisikException extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public ZisikException(String message) {
        super(message);
    }

    public ZisikException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int statusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
