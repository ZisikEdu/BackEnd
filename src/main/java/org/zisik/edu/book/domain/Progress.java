package org.zisik.edu.book.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProgressId.class)
@Table(name = "book_progress")
public class Progress {
    @Id
    @Column(name = "book_id")
    private Long bookId;
    @Id
    @Column(name = "account_id")
    private Long accountId;
    private LocalDateTime readingStartDate;
    private LocalTime readingTime;
    private int tasks;
}
