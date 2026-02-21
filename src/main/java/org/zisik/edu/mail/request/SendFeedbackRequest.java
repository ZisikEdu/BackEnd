package org.zisik.edu.mail.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendFeedbackRequest {

    @NotBlank(message = "제목은 필수입니다.")
    private String subject;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;
}
