package org.zisik.edu.flow.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.zisik.edu.exception.FlowSessionAlreadyEnded;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FlowSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;
    private Long userLibraryId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long duration;          // 초 단위
    private Integer startPage;
    private Integer endPage;
    private String videoUrl;        // 영상 모드용
    private LocalDate sessionDate;

    @Builder
    public FlowSession(Long accountId, Long userLibraryId, Integer startPage, String videoUrl) {
        this.accountId = accountId;
        this.userLibraryId = userLibraryId;
        this.startPage = startPage;
        this.videoUrl = videoUrl;
        this.startTime = LocalDateTime.now();
        this.sessionDate = LocalDate.now();
    }

    public void endSession(Integer endPage) {
        if (this.endTime != null) {
            throw new FlowSessionAlreadyEnded();
        }
        this.endTime = LocalDateTime.now();
        this.endPage = endPage;
        this.duration = Duration.between(startTime, endTime).getSeconds();
    }

    public boolean isEnded() {
        return this.endTime != null;
    }

    public int getPagesRead() {
        if (startPage == null || endPage == null) {
            return 0;
        }
        return Math.max(0, endPage - startPage);
    }
}
