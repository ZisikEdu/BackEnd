package org.zisik.edu.note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zisik.edu.note.domain.BookNote;

import java.util.Optional;

public interface BookNoteRepository extends JpaRepository<BookNote, Long> {

    Optional<BookNote> findByUserLibraryId(Long userLibraryId);

    boolean existsByUserLibraryId(Long userLibraryId);
}
