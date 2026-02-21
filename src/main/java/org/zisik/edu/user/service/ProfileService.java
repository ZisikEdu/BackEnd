package org.zisik.edu.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zisik.edu.exception.UserNotFound;
import org.zisik.edu.user.domain.Account;
import org.zisik.edu.user.domain.UserProfile;
import org.zisik.edu.user.repository.AccountRepository;
import org.zisik.edu.user.repository.UserProfileRepository;
import org.zisik.edu.user.request.UpdateProfileRequest;
import org.zisik.edu.user.response.ProfileResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {

    private final AccountRepository accountRepository;
    private final UserProfileRepository userProfileRepository;

    public ProfileResponse getProfile(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(UserNotFound::new);

        UserProfile profile = userProfileRepository.findByAccountId(accountId)
                .orElse(null);

        return ProfileResponse.from(account, profile);
    }

    @Transactional
    public ProfileResponse updateProfile(Long accountId, UpdateProfileRequest request) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(UserNotFound::new);

        UserProfile profile = userProfileRepository.findByAccountId(accountId)
                .orElseGet(() -> {
                    UserProfile newProfile = UserProfile.builder()
                            .accountId(accountId)
                            .build();
                    return userProfileRepository.save(newProfile);
                });

        if (request.getNickname() != null) {
            profile.updateNickname(request.getNickname());
        }
        if (request.getBio() != null) {
            profile.updateBio(request.getBio());
        }
        if (request.getProfileImageUrl() != null) {
            profile.updateProfileImage(request.getProfileImageUrl());
        }

        return ProfileResponse.from(account, profile);
    }
}
