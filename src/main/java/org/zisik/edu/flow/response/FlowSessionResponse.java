package org.zisik.edu.flow.response;

import lombok.Builder;
import lombok.Getter;
import org.zisik.edu.flow.domain.FlowSession;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class FlowSessionResponse {

    private Long id;
    private Long userLibraryId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long duration;
    private Integer startPage;
    private Integer endPage;
    private Integer pagesRead;
    private String videoUrl;
    private LocalDate sessionDate;
    private boolean isActive;

    public static FlowSessionResponse from(FlowSession session) {
        return FlowSessionResponse.builder()
                .id(session.getId())
                .userLibraryId(session.getUserLibraryId())
                .startTime(session.getStartTime())
                .endTime(session.getEndTime())
                .duration(session.getDuration())
                .startPage(session.getStartPage())
                .endPage(session.getEndPage())
                .pagesRead(session.getPagesRead())
                .videoUrl(session.getVideoUrl())
                .sessionDate(session.getSessionDate())
                .isActive(!session.isEnded())
                .build();
    }
}
