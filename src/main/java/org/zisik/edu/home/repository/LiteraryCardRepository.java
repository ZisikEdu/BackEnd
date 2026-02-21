package org.zisik.edu.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zisik.edu.home.domain.LiteraryCard;
import org.zisik.edu.home.domain.LiteraryType;

import java.time.LocalDate;
import java.util.Optional;

public interface LiteraryCardRepository extends JpaRepository<LiteraryCard, Long> {

    Optional<LiteraryCard> findByDisplayDateAndType(LocalDate displayDate, LiteraryType type);

    @Query("SELECT lc FROM LiteraryCard lc WHERE lc.type = :type ORDER BY lc.displayDate DESC LIMIT 1")
    Optional<LiteraryCard> findLatestByType(@Param("type") LiteraryType type);
}
