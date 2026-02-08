package org.zisik.edu.exception;

public class BookNotFound extends ZisikException {

    private static final String MESSAGE = "존재하지 않는 책입니다.";

    public BookNotFound() {
        super(MESSAGE);
    }

    @Override
    public int statusCode() {
        return 404;
    }
}
