package org.zisik.edu.library.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"accountId", "bookId"}))
public class UserLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;
    private Long bookId;

    @Enumerated(EnumType.STRING)
    private ReadingStatus status;

    private Integer currentPage;
    private Integer userRating;         // 1-5 별점
    private LocalDateTime registeredAt;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private Integer rereadCount;

    @Builder
    public UserLibrary(Long accountId, Long bookId, ReadingStatus status) {
        this.accountId = accountId;
        this.bookId = bookId;
        this.status = status;
        this.currentPage = 0;
        this.rereadCount = 0;
        this.registeredAt = LocalDateTime.now();
    }

    public void updateStatus(ReadingStatus status) {
        this.status = status;
        if (status == ReadingStatus.READING && this.startedAt == null) {
            this.startedAt = LocalDateTime.now();
        } else if (status == ReadingStatus.COMPLETE) {
            this.completedAt = LocalDateTime.now();
        }
    }

    public void updateCurrentPage(Integer page) {
        this.currentPage = page;
        if (this.status == ReadingStatus.PLAN_TO_READ && page > 0) {
            updateStatus(ReadingStatus.READING);
        }
    }

    public void updateUserRating(Integer rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.userRating = rating;
    }

    public void startReread() {
        this.rereadCount++;
        this.currentPage = 0;
        this.status = ReadingStatus.READING;
        this.startedAt = LocalDateTime.now();
        this.completedAt = null;
    }
}
