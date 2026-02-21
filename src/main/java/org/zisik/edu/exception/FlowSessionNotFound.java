package org.zisik.edu.exception;

public class FlowSessionNotFound extends ZisikException {

    private static final String MESSAGE = "집중 세션을 찾을 수 없습니다.";

    public FlowSessionNotFound() {
        super(MESSAGE);
    }

    @Override
    public int statusCode() {
        return 404;
    }
}
