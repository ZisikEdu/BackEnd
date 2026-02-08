package org.zisik.edu.exception;

public class LibraryBookNotFound extends ZisikException {

    private static final String MESSAGE = "서재에 등록된 책을 찾을 수 없습니다.";

    public LibraryBookNotFound() {
        super(MESSAGE);
    }

    @Override
    public int statusCode() {
        return 404;
    }
}
