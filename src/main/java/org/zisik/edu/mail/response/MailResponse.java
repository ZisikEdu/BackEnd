package org.zisik.edu.mail.response;

import lombok.Builder;
import lombok.Getter;
import org.zisik.edu.mail.domain.Mail;
import org.zisik.edu.mail.domain.MailType;

import java.time.LocalDateTime;

@Getter
@Builder
public class MailResponse {

    private Long id;
    private MailType type;
    private String title;
    private String content;
    private Boolean isRead;
    private LocalDateTime createdAt;

    public static MailResponse from(Mail mail) {
        return MailResponse.builder()
                .id(mail.getId())
                .type(mail.getType())
                .title(mail.getTitle())
                .content(mail.getContent())
                .isRead(mail.getIsRead())
                .createdAt(mail.getCreatedAt())
                .build();
    }
}
