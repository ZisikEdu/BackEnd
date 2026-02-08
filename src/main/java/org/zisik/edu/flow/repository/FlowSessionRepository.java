package org.zisik.edu.flow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zisik.edu.flow.domain.FlowSession;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlowSessionRepository extends JpaRepository<FlowSession, Long> {

    List<FlowSession> findByAccountIdAndUserLibraryId(Long accountId, Long userLibraryId);

    @Query("SELECT SUM(fs.duration) FROM FlowSession fs WHERE fs.accountId = :accountId")
    Long getTotalDuration(@Param("accountId") Long accountId);

    @Query("SELECT DISTINCT fs.sessionDate FROM FlowSession fs " +
           "WHERE fs.accountId = :accountId " +
           "AND YEAR(fs.sessionDate) = :year " +
           "AND MONTH(fs.sessionDate) = :month")
    List<LocalDate> getReadingDatesForMonth(
            @Param("accountId") Long accountId,
            @Param("year") int year,
            @Param("month") int month);

    @Query("SELECT fs FROM FlowSession fs " +
           "WHERE fs.accountId = :accountId AND fs.endTime IS NULL " +
           "ORDER BY fs.startTime DESC")
    Optional<FlowSession> findActiveSession(@Param("accountId") Long accountId);

    @Query("SELECT DISTINCT fs.sessionDate FROM FlowSession fs " +
           "WHERE fs.accountId = :accountId " +
           "ORDER BY fs.sessionDate DESC")
    List<LocalDate> getAllReadingDates(@Param("accountId") Long accountId);
}
