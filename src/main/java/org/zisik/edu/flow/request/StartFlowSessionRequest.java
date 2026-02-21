package org.zisik.edu.flow.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartFlowSessionRequest {

    @NotNull(message = "서재 책 ID는 필수입니다.")
    private Long userLibraryId;

    private Integer startPage;
    private String videoUrl;
}
