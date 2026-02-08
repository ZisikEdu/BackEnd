package org.zisik.edu.user.response;

import lombok.Builder;
import lombok.Getter;
import org.zisik.edu.user.domain.Account;
import org.zisik.edu.user.domain.UserProfile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class ProfileResponse {

    private Long id;
    private String email;
    private String username;
    private String nickname;
    private String profileImageUrl;
    private String bio;
    private Integer streakDays;
    private Integer maxStreakDays;
    private LocalDate lastReadingDate;
    private LocalDateTime updatedAt;

    public static ProfileResponse from(Account account, UserProfile profile) {
        ProfileResponseBuilder builder = ProfileResponse.builder()
                .id(account.getId())
                .email(account.getEmail())
                .username(account.getUsername());

        if (profile != null) {
            builder.nickname(profile.getNickname())
                   .profileImageUrl(profile.getProfileImageUrl())
                   .bio(profile.getBio())
                   .streakDays(profile.getStreakDays())
                   .maxStreakDays(profile.getMaxStreakDays())
                   .lastReadingDate(profile.getLastReadingDate())
                   .updatedAt(profile.getUpdatedAt());
        }

        return builder.build();
    }
}
