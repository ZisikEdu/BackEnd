package org.zisik.edu.exception;

public class Unauthorized extends ZisikException{

    private static final String MESSAGE = "인증 필요합니다.";

    public Unauthorized() {
        super(MESSAGE);
    }

    @Override
    public int statusCode() {
        return 401;
    }
}
