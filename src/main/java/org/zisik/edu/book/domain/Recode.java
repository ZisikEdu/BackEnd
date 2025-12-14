package org.zisik.edu.book.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recode {
    @Id
    private Long id;
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "account_id")
    private Long accountId;
    private LocalTime readingTime;
    private LocalDateTime recodeDate;
    private int startPage;
    private int endPage;
}
