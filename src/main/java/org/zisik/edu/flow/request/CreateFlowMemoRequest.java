package org.zisik.edu.flow.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFlowMemoRequest {

    @NotBlank(message = "메모 내용은 필수입니다.")
    private String content;

    private Integer pageNumber;
}
