package org.zisik.edu.statistics.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StatisticsSummaryResponse {

    private Long completedBooks;         // 완독 수
    private Long totalFocusSeconds;      // 총 집중 시간 (초)
    private String totalFocusTimeFormatted;  // 포맷된 집중 시간
    private Integer currentStreak;       // 현재 연속 독서일
    private Integer maxStreak;           // 최대 연속 독서일

    public static StatisticsSummaryResponse of(Long completedBooks, Long totalFocusSeconds,
                                                Integer currentStreak, Integer maxStreak) {
        String formatted = formatDuration(totalFocusSeconds != null ? totalFocusSeconds : 0);

        return StatisticsSummaryResponse.builder()
                .completedBooks(completedBooks != null ? completedBooks : 0)
                .totalFocusSeconds(totalFocusSeconds != null ? totalFocusSeconds : 0)
                .totalFocusTimeFormatted(formatted)
                .currentStreak(currentStreak != null ? currentStreak : 0)
                .maxStreak(maxStreak != null ? maxStreak : 0)
                .build();
    }

    private static String formatDuration(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;

        if (hours > 0) {
            return String.format("%d시간 %d분", hours, minutes);
        } else {
            return String.format("%d분", minutes);
        }
    }
}
