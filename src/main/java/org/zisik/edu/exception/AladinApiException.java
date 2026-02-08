package org.zisik.edu.exception;

public class AladinApiException extends ZisikException {

    private static final String MESSAGE = "알라딘 API 호출 중 오류가 발생했습니다.";

    public AladinApiException() {
        super(MESSAGE);
    }

    public AladinApiException(String message) {
        super(message);
    }

    public AladinApiException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int statusCode() {
        return 502;
    }
}
