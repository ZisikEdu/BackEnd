package org.zisik.edu.exception;

import lombok.Getter;

/*
* status -> 400
*/
@Getter
public class InvalidRequest extends ZisikException {

    private static final String MESSAGE = "잘못된 요청입니다.";
    public InvalidRequest() {
        super(MESSAGE);
    }

    public InvalidRequest(String message) {
        super(message);
    }
    public InvalidRequest(String fieldName,String message) {
        super(MESSAGE);
        addValidation(fieldName,message);
    }

    @Override
    public int statusCode() {
        return 400;
    }
}
