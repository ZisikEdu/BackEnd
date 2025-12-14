package org.zisik.edu.book.repositroy;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.zisik.edu.book.domain.Book;
import org.zisik.edu.book.request.BookSearch;
import java.util.List;

import static org.zisik.edu.book.domain.QBook.book;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Book> getList(BookSearch bookSearch) {

        long totalCount = jpaQueryFactory.select(book.count())
                .from(book)
                .fetchFirst();
        List<Book> items = jpaQueryFactory.selectFrom(book)
                .limit(bookSearch.getSize())
                .offset(bookSearch.getOffset())
                .orderBy(book.id.desc())
                .fetch();

        return new PageImpl<>(items, bookSearch.getPageable(), totalCount);
    }
}
