package org.zisik.edu.exception;

public class AlreadyInLibrary extends ZisikException {

    private static final String MESSAGE = "이미 서재에 등록된 책입니다.";

    public AlreadyInLibrary() {
        super(MESSAGE);
    }

    @Override
    public int statusCode() {
        return 409;
    }
}
