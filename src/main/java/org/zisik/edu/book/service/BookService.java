package org.zisik.edu.book.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zisik.edu.book.domain.Book;
import org.zisik.edu.book.repositroy.*;
import org.zisik.edu.book.request.BookSearch;
import org.zisik.edu.book.request.ManualBookCreateRequest;
import org.zisik.edu.book.response.BookResponse;
import org.zisik.edu.book.response.PagingResponse;
import org.zisik.edu.exception.BookNotFound;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    /**
     * 로컬 DB에서 책 목록 조회 (페이징)
     */
    public PagingResponse<BookResponse> getList(BookSearch bookSearch) {
        Page<Book> bookPage = bookRepository.getList(bookSearch);
        PagingResponse<BookResponse> bookList = new PagingResponse<>(bookPage, BookResponse.class);
        return bookList;
    }

    /**
     * 로컬 DB에서 책 검색 (제목, 저자, ISBN)
     */
    public Page<BookResponse> searchBooks(String query, int page, int size) {
        BookSearch bookSearch = BookSearch.builder()
                .query(query)
                .page(page)
                .size(size)
                .build();

        Page<Book> bookPage = bookRepository.getList(bookSearch);
        return bookPage.map(BookResponse::from);
    }

    /**
     * 로컬 DB에서 ISBN으로 책 조회
     */
    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(BookNotFound::new);
    }

    /**
     * 로컬 DB에서 ID로 책 조회
     */
    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(BookNotFound::new);
    }

    /**
     * 직접 책 등록
     */
    @Transactional
    public Book createManualBook(ManualBookCreateRequest request) {
        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .category(request.getCategory())
                .genre(request.getGenre())
                .imageUrl(request.getImageUrl())
                .description(request.getDescription())
                .publisher(request.getPublisher())
                .totalPages(request.getTotalPages())
                .isManualEntry(true)
                .build();

        return bookRepository.save(book);
    }

    @Transactional
    public Book addBook(Book book) {
        bookRepository.save(book);
        return book;
    }
}
