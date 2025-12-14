package org.zisik.edu.book.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.zisik.edu.book.domain.Book;
import org.zisik.edu.book.repositroy.*;
import org.zisik.edu.book.request.BookSearch;
import org.zisik.edu.book.response.BookResponse;
import org.zisik.edu.book.response.PagingResponse;


@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository  bookRepository;

    public PagingResponse<BookResponse> getList(BookSearch bookSearch) {
        Page<Book> bookPage = bookRepository.getList(bookSearch);
        PagingResponse<BookResponse> bookList = new PagingResponse<>(bookPage,BookResponse.class);

        return bookList;
    }

    public Book addBook(Book book) {
        book.validate();
        bookRepository.save(book);
        return book;
    }

    //TODO 제목,작가,키워드, 전체 등 다양한 검색조건을 통해서 검색

}
