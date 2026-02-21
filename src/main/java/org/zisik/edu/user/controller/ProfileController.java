package org.zisik.edu.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.zisik.edu.config.auth.PrincipalDetails;
import org.zisik.edu.user.request.UpdateProfileRequest;
import org.zisik.edu.user.response.ProfileResponse;
import org.zisik.edu.user.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ProfileResponse getProfile(
            @AuthenticationPrincipal PrincipalDetails principal) {
        return profileService.getProfile(principal.getAccount().getId());
    }

    @PatchMapping
    public ProfileResponse updateProfile(
            @AuthenticationPrincipal PrincipalDetails principal,
            @RequestBody UpdateProfileRequest request) {
        return profileService.updateProfile(principal.getAccount().getId(), request);
    }
}
