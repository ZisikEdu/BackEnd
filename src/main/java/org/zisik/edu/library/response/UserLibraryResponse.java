package org.zisik.edu.library.response;

import lombok.Builder;
import lombok.Getter;
import org.zisik.edu.book.domain.Book;
import org.zisik.edu.library.domain.ReadingStatus;
import org.zisik.edu.library.domain.UserLibrary;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserLibraryResponse {

    private Long id;
    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookImageUrl;
    private Integer totalPages;
    private ReadingStatus status;
    private Integer currentPage;
    private Integer userRating;
    private Integer progressPercent;
    private LocalDateTime registeredAt;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private Integer rereadCount;

    public static UserLibraryResponse from(UserLibrary userLibrary, Book book) {
        Integer progress = 0;
        if (book.getTotalPages() != null && book.getTotalPages() > 0 && userLibrary.getCurrentPage() != null) {
            progress = (int) Math.round((double) userLibrary.getCurrentPage() / book.getTotalPages() * 100);
        }

        return UserLibraryResponse.builder()
                .id(userLibrary.getId())
                .bookId(book.getId())
                .bookTitle(book.getTitle())
                .bookAuthor(book.getAuthor())
                .bookImageUrl(book.getImageUrl())
                .totalPages(book.getTotalPages())
                .status(userLibrary.getStatus())
                .currentPage(userLibrary.getCurrentPage())
                .userRating(userLibrary.getUserRating())
                .progressPercent(progress)
                .registeredAt(userLibrary.getRegisteredAt())
                .startedAt(userLibrary.getStartedAt())
                .completedAt(userLibrary.getCompletedAt())
                .rereadCount(userLibrary.getRereadCount())
                .build();
    }
}
