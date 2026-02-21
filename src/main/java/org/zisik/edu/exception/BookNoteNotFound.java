package org.zisik.edu.exception;

public class BookNoteNotFound extends ZisikException {

    private static final String MESSAGE = "독서 노트를 찾을 수 없습니다.";

    public BookNoteNotFound() {
        super(MESSAGE);
    }

    @Override
    public int statusCode() {
        return 404;
    }
}
