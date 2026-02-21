package org.zisik.edu.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;
    private String nickname;
    private String profileImageUrl;
    private String bio;
    private Integer streakDays;
    private Integer maxStreakDays;
    private LocalDate lastReadingDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public UserProfile(Long accountId, String nickname, String profileImageUrl, String bio) {
        this.accountId = accountId;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.bio = bio;
        this.streakDays = 0;
        this.maxStreakDays = 0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateBio(String bio) {
        this.bio = bio;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateProfileImage(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateReadingStreak(LocalDate today) {
        if (lastReadingDate == null) {
            // 첫 독서
            this.streakDays = 1;
        } else if (lastReadingDate.equals(today)) {
            // 오늘 이미 독서함
            return;
        } else if (lastReadingDate.plusDays(1).equals(today)) {
            // 연속 독서
            this.streakDays++;
        } else {
            // 연속 끊김
            this.streakDays = 1;
        }

        this.lastReadingDate = today;
        if (this.streakDays > this.maxStreakDays) {
            this.maxStreakDays = this.streakDays;
        }
        this.updatedAt = LocalDateTime.now();
    }
}
