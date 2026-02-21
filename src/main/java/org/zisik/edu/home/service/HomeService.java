package org.zisik.edu.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zisik.edu.flow.repository.FlowSessionRepository;
import org.zisik.edu.home.domain.LiteraryType;
import org.zisik.edu.home.repository.LiteraryCardRepository;
import org.zisik.edu.home.response.LiteraryCardResponse;
import org.zisik.edu.home.response.ProfileCardResponse;
import org.zisik.edu.home.response.ReadingCardResponse;
import org.zisik.edu.library.repository.UserLibraryRepository;
import org.zisik.edu.library.service.LibraryService;
import org.zisik.edu.user.domain.UserProfile;
import org.zisik.edu.user.repository.UserProfileRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {

    private final UserProfileRepository userProfileRepository;
    private final UserLibraryRepository userLibraryRepository;
    private final FlowSessionRepository flowSessionRepository;
    private final LiteraryCardRepository literaryCardRepository;
    private final LibraryService libraryService;

    public ProfileCardResponse getProfileCard(Long accountId) {
        UserProfile profile = userProfileRepository.findByAccountId(accountId).orElse(null);

        String nickname = profile != null ? profile.getNickname() : "독서가";
        String profileImageUrl = profile != null ? profile.getProfileImageUrl() : null;
        Integer currentStreak = profile != null ? profile.getStreakDays() : 0;

        Long completedBooks = userLibraryRepository.countCompletedBooks(accountId);
        Long totalFocusSeconds = flowSessionRepository.getTotalDuration(accountId);

        return ProfileCardResponse.of(nickname, profileImageUrl, completedBooks, totalFocusSeconds, currentStreak);
    }

    public LiteraryCardResponse getLiteraryCard() {
        LocalDate today = LocalDate.now();

        // 오늘 날짜의 문학 카드 찾기
        return literaryCardRepository.findByDisplayDateAndType(today, LiteraryType.DAILY)
                .map(LiteraryCardResponse::from)
                // 없으면 가장 최근 카드
                .orElseGet(() -> literaryCardRepository.findLatestByType(LiteraryType.DAILY)
                        .map(LiteraryCardResponse::from)
                        .orElse(null));
    }

    public ReadingCardResponse getReadingCard(Long accountId) {
        return ReadingCardResponse.from(libraryService.getCurrentlyReading(accountId));
    }
}
