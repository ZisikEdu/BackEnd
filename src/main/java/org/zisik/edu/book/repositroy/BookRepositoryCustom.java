package org.zisik.edu.book.repositroy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.zisik.edu.book.domain.Book;
import org.zisik.edu.book.request.BookSearch;

public interface BookRepositoryCustom {
    Page<Book> getList(BookSearch bookSearch);
}
