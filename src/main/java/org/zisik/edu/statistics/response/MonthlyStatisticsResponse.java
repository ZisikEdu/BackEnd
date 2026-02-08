package org.zisik.edu.statistics.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class MonthlyStatisticsResponse {

    private int year;
    private List<MonthData> months;
    private int totalBooksYear;

    @Getter
    @Builder
    public static class MonthData {
        private int month;
        private int completedBooks;
    }

    public static MonthlyStatisticsResponse from(int year, Map<Integer, Long> monthlyData) {
        List<MonthData> months = monthlyData.entrySet().stream()
                .map(entry -> MonthData.builder()
                        .month(entry.getKey())
                        .completedBooks(entry.getValue().intValue())
                        .build())
                .sorted((a, b) -> Integer.compare(a.getMonth(), b.getMonth()))
                .toList();

        int total = months.stream().mapToInt(MonthData::getCompletedBooks).sum();

        return MonthlyStatisticsResponse.builder()
                .year(year)
                .months(months)
                .totalBooksYear(total)
                .build();
    }
}
