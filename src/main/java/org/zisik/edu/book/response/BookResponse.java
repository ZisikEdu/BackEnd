package org.zisik.edu.book.response;

import lombok.Builder;
import lombok.Getter;
import org.zisik.edu.book.domain.Book;

import java.time.LocalDateTime;

@Getter
@Builder
public class BookResponse {

    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String category;
    private String genre;
    private String imageUrl;
    private String description;
    private String publisher;
    private LocalDateTime publishedDate;
    private Integer totalPages;
    private Double rating;
    private String aladinItemId;
    private Boolean isManualEntry;
    private LocalDateTime createdAt;

    public static BookResponse from(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .category(book.getCategory())
                .genre(book.getGenre())
                .imageUrl(book.getImageUrl())
                .description(book.getDescription())
                .publisher(book.getPublisher())
                .publishedDate(book.getPublishedDate())
                .totalPages(book.getTotalPages())
                .rating(book.getRating())
                .aladinItemId(book.getAladinItemId())
                .isManualEntry(book.getIsManualEntry())
                .createdAt(book.getCreatedAt())
                .build();
    }
}
