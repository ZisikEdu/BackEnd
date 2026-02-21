package org.zisik.edu.book.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zisik.edu.book.domain.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
    Optional<Book> findByIsbn(String isbn);
    Optional<Book> findByAladinItemId(String aladinItemId);
}
