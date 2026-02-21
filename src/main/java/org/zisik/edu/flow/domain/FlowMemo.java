package org.zisik.edu.flow.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FlowMemo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long flowSessionId;

    @Lob
    private String content;

    private LocalDateTime createdAt;
    private Integer pageNumber;

    @Builder
    public FlowMemo(Long flowSessionId, String content, Integer pageNumber) {
        this.flowSessionId = flowSessionId;
        this.content = content;
        this.pageNumber = pageNumber;
        this.createdAt = LocalDateTime.now();
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
