package org.zisik.edu.external.aladin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zisik.edu.book.domain.Book;
import org.zisik.edu.book.repositroy.BookRepository;
import org.zisik.edu.book.response.BookResponse;
import org.zisik.edu.book.response.BookSearchResponse;
import org.zisik.edu.exception.BookNotFound;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AladinService {

    private final AladinApiClient aladinApiClient;
    private final BookRepository bookRepository;

    /**
     * 알라딘 API에서 책 검색
     */
    public BookSearchResponse searchFromAladin(String query, int page, int size) {
        AladinSearchResult result = aladinApiClient.searchBooks(query, page, size);
        return BookSearchResponse.from(result, page, size);
    }

    /**
     * 알라딘 API에서 ISBN으로 책 조회
     */
    public BookSearchResponse getFromAladinByIsbn(String isbn) {
        AladinSearchResult result = aladinApiClient.getBookByIsbn(isbn);
        return BookSearchResponse.from(result, 1, 1);
    }

    /**
     * 알라딘 API에서 책 검색 후 로컬 DB에 저장
     */
    @Transactional
    public BookResponse importBookByIsbn(String isbn) {
        // 이미 DB에 있는지 확인
        Optional<Book> existingBook = bookRepository.findByIsbn(isbn);
        if (existingBook.isPresent()) {
            return BookResponse.from(existingBook.get());
        }

        // 알라딘 API에서 조회
        AladinSearchResult result = aladinApiClient.getBookByIsbn(isbn);
        if (result.getItem() == null || result.getItem().isEmpty()) {
            throw new BookNotFound();
        }

        Book book = saveBookFromAladinItem(result.getItem().get(0));
        return BookResponse.from(book);
    }

    /**
     * 알라딘 ItemId로 책을 로컬 DB에 저장
     */
    @Transactional
    public BookResponse importBookByItemId(String itemId) {
        // 이미 DB에 있는지 확인
        Optional<Book> existingBook = bookRepository.findByAladinItemId(itemId);
        if (existingBook.isPresent()) {
            return BookResponse.from(existingBook.get());
        }

        // 알라딘 API에서 조회 (ItemId로 조회는 ISBN 조회와 유사하게 처리)
        // 참고: 알라딘 API는 ItemId로 직접 조회 가능
        AladinSearchResult result = aladinApiClient.getBookByIsbn(itemId);
        if (result.getItem() == null || result.getItem().isEmpty()) {
            throw new BookNotFound();
        }

        Book book = saveBookFromAladinItem(result.getItem().get(0));
        return BookResponse.from(book);
    }

    /**
     * 알라딘 검색 결과에서 여러 책을 한번에 로컬 DB에 저장
     */
    @Transactional
    public List<BookResponse> importBooksFromSearch(String query, int page, int size) {
        AladinSearchResult result = aladinApiClient.searchBooks(query, page, size);

        if (result.getItem() == null || result.getItem().isEmpty()) {
            return List.of();
        }

        return result.getItem().stream()
                .map(item -> {
                    // 이미 DB에 있는지 확인
                    String isbn = item.getIsbn13() != null ? item.getIsbn13() : item.getIsbn();
                    Optional<Book> existingBook = bookRepository.findByIsbn(isbn);
                    if (existingBook.isPresent()) {
                        return BookResponse.from(existingBook.get());
                    }
                    // 없으면 저장
                    Book book = saveBookFromAladinItem(item);
                    return BookResponse.from(book);
                })
                .toList();
    }

    private Book saveBookFromAladinItem(AladinSearchResult.AladinBookItem item) {
        LocalDateTime pubDate = null;
        if (item.getPubDate() != null && !item.getPubDate().isEmpty()) {
            try {
                pubDate = LocalDateTime.parse(item.getPubDate() + "T00:00:00");
            } catch (Exception e) {
                log.warn("Failed to parse publication date: {}", item.getPubDate());
            }
        }

        Integer totalPages = null;
        if (item.getSubInfo() != null && item.getSubInfo().getItemPage() > 0) {
            totalPages = item.getSubInfo().getItemPage();
        }

        Book book = Book.builder()
                .isbn(item.getIsbn13() != null ? item.getIsbn13() : item.getIsbn())
                .title(item.getTitle())
                .author(item.getAuthor())
                .category(item.getCategoryName())
                .imageUrl(item.getCover())
                .description(item.getDescription())
                .publisher(item.getPublisher())
                .publishedDate(pubDate)
                .totalPages(totalPages)
                .rating(item.getCustomerReviewRank() > 0 ? (double) item.getCustomerReviewRank() / 2 : null)
                .aladinItemId(item.getItemId())
                .isManualEntry(false)
                .build();

        return bookRepository.save(book);
    }
}
