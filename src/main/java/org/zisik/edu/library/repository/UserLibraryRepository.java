package org.zisik.edu.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zisik.edu.library.domain.ReadingStatus;
import org.zisik.edu.library.domain.UserLibrary;

import java.util.List;
import java.util.Optional;

public interface UserLibraryRepository extends JpaRepository<UserLibrary, Long> {

    Optional<UserLibrary> findByAccountIdAndBookId(Long accountId, Long bookId);

    boolean existsByAccountIdAndBookId(Long accountId, Long bookId);

    Page<UserLibrary> findByAccountId(Long accountId, Pageable pageable);

    Page<UserLibrary> findByAccountIdAndStatus(Long accountId, ReadingStatus status, Pageable pageable);

    List<UserLibrary> findByAccountIdAndStatus(Long accountId, ReadingStatus status);

    @Query("SELECT COUNT(ul) FROM UserLibrary ul WHERE ul.accountId = :accountId AND ul.status = 'COMPLETE'")
    Long countCompletedBooks(@Param("accountId") Long accountId);

    @Query("SELECT ul FROM UserLibrary ul WHERE ul.accountId = :accountId AND ul.status = 'READING' ORDER BY ul.startedAt DESC")
    List<UserLibrary> findCurrentlyReading(@Param("accountId") Long accountId);

    @Query("SELECT MONTH(ul.completedAt) as month, COUNT(ul) as count FROM UserLibrary ul " +
           "WHERE ul.accountId = :accountId AND YEAR(ul.completedAt) = :year AND ul.status = 'COMPLETE' " +
           "GROUP BY MONTH(ul.completedAt)")
    List<Object[]> getMonthlyCompletedBooks(@Param("accountId") Long accountId, @Param("year") int year);
}
