package org.zisik.edu.exception;

public class ValidationException extends ZisikException{

    public ValidationException(String message) {
        super(message);
    }

    @Override
    public int statusCode() {
        return 400;
    }
}
