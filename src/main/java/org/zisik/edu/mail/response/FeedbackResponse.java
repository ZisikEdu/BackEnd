package org.zisik.edu.mail.response;

import lombok.Builder;
import lombok.Getter;
import org.zisik.edu.mail.domain.Feedback;
import org.zisik.edu.mail.domain.FeedbackStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedbackResponse {

    private Long id;
    private String subject;
    private String content;
    private FeedbackStatus status;
    private LocalDateTime createdAt;

    public static FeedbackResponse from(Feedback feedback) {
        return FeedbackResponse.builder()
                .id(feedback.getId())
                .subject(feedback.getSubject())
                .content(feedback.getContent())
                .status(feedback.getStatus())
                .createdAt(feedback.getCreatedAt())
                .build();
    }
}
