package org.zisik.edu.book.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zisik.edu.book.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
}
