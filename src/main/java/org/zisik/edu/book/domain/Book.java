package org.zisik.edu.book.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private String title;                   // 제목
    private String author;                  // 저자
    private String category;                // 카테고리
    private String genre;                   // 장르 태그
    private String imageUrl;                // 대표사진

    @Lob
    private String description;             // 설명

    private String publisher;               // 출판사
    private LocalDateTime publishedDate;    // 출간일
    private Integer totalPages;             // 총 페이지수
    private Double rating;                  // 평균 별점
    private String aladinItemId;            // 알라딘 API 연동 ID
    private Boolean isManualEntry;          // 직접 등록 여부
    private LocalDateTime createdAt;

    @Builder
    public Book(String isbn, String title, String author, String category, String genre,
                String imageUrl, String description, String publisher, LocalDateTime publishedDate,
                Integer totalPages, Double rating, String aladinItemId, Boolean isManualEntry) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.genre = genre;
        this.imageUrl = imageUrl;
        this.description = description;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.totalPages = totalPages;
        this.rating = rating;
        this.aladinItemId = aladinItemId;
        this.isManualEntry = isManualEntry;
        this.createdAt = LocalDateTime.now();
    }

    public void updateRating(Double newRating) {
        this.rating = newRating;
    }
}
