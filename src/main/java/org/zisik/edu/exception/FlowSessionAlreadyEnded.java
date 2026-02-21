package org.zisik.edu.exception;

public class FlowSessionAlreadyEnded extends ZisikException {

    private static final String MESSAGE = "이미 종료된 집중 세션입니다.";

    public FlowSessionAlreadyEnded() {
        super(MESSAGE);
    }

    @Override
    public int statusCode() {
        return 400;
    }
}
