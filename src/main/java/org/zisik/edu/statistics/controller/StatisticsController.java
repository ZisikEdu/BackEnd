package org.zisik.edu.statistics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.zisik.edu.config.auth.PrincipalDetails;
import org.zisik.edu.statistics.response.CalendarResponse;
import org.zisik.edu.statistics.response.MonthlyStatisticsResponse;
import org.zisik.edu.statistics.response.StatisticsSummaryResponse;
import org.zisik.edu.statistics.service.StatisticsService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/summary")
    public StatisticsSummaryResponse getSummary(
            @AuthenticationPrincipal PrincipalDetails principal) {
        return statisticsService.getSummary(principal.getAccount().getId());
    }

    @GetMapping("/monthly")
    public MonthlyStatisticsResponse getMonthlyStatistics(
            @AuthenticationPrincipal PrincipalDetails principal,
            @RequestParam(required = false) Integer year) {
        int targetYear = year != null ? year : LocalDate.now().getYear();
        return statisticsService.getMonthlyStatistics(principal.getAccount().getId(), targetYear);
    }

    @GetMapping("/calendar")
    public CalendarResponse getCalendar(
            @AuthenticationPrincipal PrincipalDetails principal,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month) {
        LocalDate now = LocalDate.now();
        int targetYear = year != null ? year : now.getYear();
        int targetMonth = month != null ? month : now.getMonthValue();
        return statisticsService.getCalendar(principal.getAccount().getId(), targetYear, targetMonth);
    }
}
