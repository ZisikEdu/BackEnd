package org.zisik.edu.book.repositroy;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.StringUtils;
import org.zisik.edu.book.domain.Book;
import org.zisik.edu.book.request.BookSearch;
import java.util.List;

import static org.zisik.edu.book.domain.QBook.book;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Book> getList(BookSearch bookSearch) {
        BooleanBuilder builder = new BooleanBuilder();

        // 검색어가 있으면 제목, 저자, ISBN에서 검색
        if (StringUtils.hasText(bookSearch.getQuery())) {
            String query = bookSearch.getQuery();
            builder.and(
                book.title.containsIgnoreCase(query)
                    .or(book.author.containsIgnoreCase(query))
                    .or(book.isbn.containsIgnoreCase(query))
            );
        }

        long totalCount = jpaQueryFactory.select(book.count())
                .from(book)
                .where(builder)
                .fetchFirst();

        List<Book> items = jpaQueryFactory.selectFrom(book)
                .where(builder)
                .limit(bookSearch.getSize())
                .offset(bookSearch.getOffset())
                .orderBy(book.id.desc())
                .fetch();

        return new PageImpl<>(items, bookSearch.getPageable(), totalCount);
    }
}
