package org.zisik.edu.book.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zisik.edu.book.domain.Book;
import org.zisik.edu.book.request.ManualBookCreateRequest;
import org.zisik.edu.book.response.BookResponse;
import org.zisik.edu.book.response.BookSearchResponse;
import org.zisik.edu.book.service.BookService;
import org.zisik.edu.external.aladin.AladinService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AladinService aladinService;

    // ==================== 로컬 DB 검색 ====================

    /**
     * 로컬 DB에서 책 검색 (제목, 저자, ISBN)
     */
    @GetMapping("/search")
    public Page<BookResponse> searchBooks(
            @RequestParam String query,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return bookService.searchBooks(query, page, size);
    }

    /**
     * 로컬 DB에서 ISBN으로 책 조회
     */
    @GetMapping("/isbn/{isbn}")
    public BookResponse getBookByIsbn(@PathVariable String isbn) {
        Book book = bookService.getBookByIsbn(isbn);
        return BookResponse.from(book);
    }

    /**
     * 로컬 DB에서 ID로 책 조회
     */
    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable Long id) {
        Book book = bookService.getBook(id);
        return BookResponse.from(book);
    }

    /**
     * 직접 책 등록
     */
    @PostMapping("/manual")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createManualBook(@Valid @RequestBody ManualBookCreateRequest request) {
        Book book = bookService.createManualBook(request);
        return BookResponse.from(book);
    }

    // ==================== 알라딘 API ====================

    /**
     * 알라딘 API에서 책 검색 (로컬 DB 저장 X)
     */
    @GetMapping("/aladin/search")
    public BookSearchResponse searchFromAladin(
            @RequestParam String query,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return aladinService.searchFromAladin(query, page, size);
    }

    /**
     * 알라딘 API에서 ISBN으로 책 조회 (로컬 DB 저장 X)
     */
    @GetMapping("/aladin/isbn/{isbn}")
    public BookSearchResponse getFromAladinByIsbn(@PathVariable String isbn) {
        return aladinService.getFromAladinByIsbn(isbn);
    }

    /**
     * 알라딘에서 ISBN으로 책을 가져와 로컬 DB에 저장
     */
    @PostMapping("/aladin/import/isbn/{isbn}")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse importBookByIsbn(@PathVariable String isbn) {
        return aladinService.importBookByIsbn(isbn);
    }

    /**
     * 알라딘에서 ItemId로 책을 가져와 로컬 DB에 저장
     */
    @PostMapping("/aladin/import/item/{itemId}")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse importBookByItemId(@PathVariable String itemId) {
        return aladinService.importBookByItemId(itemId);
    }

    /**
     * 알라딘 검색 결과를 로컬 DB에 일괄 저장
     */
    @PostMapping("/aladin/import/search")
    @ResponseStatus(HttpStatus.CREATED)
    public List<BookResponse> importBooksFromSearch(
            @RequestParam String query,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return aladinService.importBooksFromSearch(query, page, size);
    }
}
