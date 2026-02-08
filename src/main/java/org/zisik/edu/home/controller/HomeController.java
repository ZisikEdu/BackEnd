package org.zisik.edu.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zisik.edu.config.auth.PrincipalDetails;
import org.zisik.edu.home.response.LiteraryCardResponse;
import org.zisik.edu.home.response.ProfileCardResponse;
import org.zisik.edu.home.response.ReadingCardResponse;
import org.zisik.edu.home.service.HomeService;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/profile-card")
    public ProfileCardResponse getProfileCard(
            @AuthenticationPrincipal PrincipalDetails principal) {
        return homeService.getProfileCard(principal.getAccount().getId());
    }

    @GetMapping("/literary-card")
    public LiteraryCardResponse getLiteraryCard() {
        return homeService.getLiteraryCard();
    }

    @GetMapping("/reading-card")
    public ReadingCardResponse getReadingCard(
            @AuthenticationPrincipal PrincipalDetails principal) {
        return homeService.getReadingCard(principal.getAccount().getId());
    }
}
