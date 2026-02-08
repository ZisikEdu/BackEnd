package org.zisik.edu.statistics.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class CalendarResponse {

    private int year;
    private int month;
    private List<LocalDate> readingDates;
    private int totalReadingDays;

    public static CalendarResponse from(int year, int month, List<LocalDate> dates) {
        return CalendarResponse.builder()
                .year(year)
                .month(month)
                .readingDates(dates)
                .totalReadingDays(dates.size())
                .build();
    }
}
