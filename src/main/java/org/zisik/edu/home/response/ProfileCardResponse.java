package org.zisik.edu.home.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileCardResponse {

    private String nickname;
    private String profileImageUrl;
    private Long completedBooks;
    private String totalFocusTime;
    private Integer currentStreak;

    public static ProfileCardResponse of(String nickname, String profileImageUrl,
                                          Long completedBooks, Long totalFocusSeconds,
                                          Integer currentStreak) {
        return ProfileCardResponse.builder()
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .completedBooks(completedBooks != null ? completedBooks : 0)
                .totalFocusTime(formatDuration(totalFocusSeconds != null ? totalFocusSeconds : 0))
                .currentStreak(currentStreak != null ? currentStreak : 0)
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
