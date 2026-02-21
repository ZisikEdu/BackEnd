package org.zisik.edu.flow.response;

import lombok.Builder;
import lombok.Getter;
import org.zisik.edu.flow.domain.FlowMemo;

import java.time.LocalDateTime;

@Getter
@Builder
public class FlowMemoResponse {

    private Long id;
    private Long flowSessionId;
    private String content;
    private Integer pageNumber;
    private LocalDateTime createdAt;

    public static FlowMemoResponse from(FlowMemo memo) {
        return FlowMemoResponse.builder()
                .id(memo.getId())
                .flowSessionId(memo.getFlowSessionId())
                .content(memo.getContent())
                .pageNumber(memo.getPageNumber())
                .createdAt(memo.getCreatedAt())
                .build();
    }
}
