package org.zisik.edu.statistics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zisik.edu.flow.repository.FlowSessionRepository;
import org.zisik.edu.library.repository.UserLibraryRepository;
import org.zisik.edu.statistics.response.CalendarResponse;
import org.zisik.edu.statistics.response.MonthlyStatisticsResponse;
import org.zisik.edu.statistics.response.StatisticsSummaryResponse;
import org.zisik.edu.user.domain.UserProfile;
import org.zisik.edu.user.repository.UserProfileRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatisticsService {

    private final UserLibraryRepository userLibraryRepository;
    private final FlowSessionRepository flowSessionRepository;
    private final UserProfileRepository userProfileRepository;

    public StatisticsSummaryResponse getSummary(Long accountId) {
        Long completedBooks = userLibraryRepository.countCompletedBooks(accountId);
        Long totalFocusSeconds = flowSessionRepository.getTotalDuration(accountId);

        UserProfile profile = userProfileRepository.findByAccountId(accountId)
                .orElse(null);

        Integer currentStreak = profile != null ? profile.getStreakDays() : 0;
        Integer maxStreak = profile != null ? profile.getMaxStreakDays() : 0;

        return StatisticsSummaryResponse.of(completedBooks, totalFocusSeconds, currentStreak, maxStreak);
    }

    public MonthlyStatisticsResponse getMonthlyStatistics(Long accountId, int year) {
        List<Object[]> results = userLibraryRepository.getMonthlyCompletedBooks(accountId, year);

        Map<Integer, Long> monthlyData = new HashMap<>();
        // 12개월 초기화
        for (int i = 1; i <= 12; i++) {
            monthlyData.put(i, 0L);
        }

        // 결과 적용
        for (Object[] row : results) {
            Integer month = (Integer) row[0];
            Long count = (Long) row[1];
            monthlyData.put(month, count);
        }

        return MonthlyStatisticsResponse.from(year, monthlyData);
    }

    public CalendarResponse getCalendar(Long accountId, int year, int month) {
        List<LocalDate> readingDates = flowSessionRepository.getReadingDatesForMonth(accountId, year, month);
        return CalendarResponse.from(year, month, readingDates);
    }
}
