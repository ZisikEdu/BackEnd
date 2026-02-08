package org.zisik.edu.mail.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.zisik.edu.config.auth.PrincipalDetails;
import org.zisik.edu.mail.request.SendFeedbackRequest;
import org.zisik.edu.mail.response.FeedbackResponse;
import org.zisik.edu.mail.response.MailResponse;
import org.zisik.edu.mail.service.MailService;

@RestController
@RequestMapping("/api/mailbox")
@RequiredArgsConstructor
public class MailboxController {

    private final MailService mailService;

    @GetMapping("/notices")
    public Page<MailResponse> getNotices(Pageable pageable) {
        return mailService.getNotices(pageable);
    }

    @GetMapping("/mail")
    public Page<MailResponse> getPersonalMails(
            @AuthenticationPrincipal PrincipalDetails principal,
            Pageable pageable) {
        return mailService.getPersonalMails(principal.getAccount().getId(), pageable);
    }

    @PatchMapping("/{id}/read")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void markAsRead(
            @AuthenticationPrincipal PrincipalDetails principal,
            @PathVariable Long id) {
        mailService.markAsRead(principal.getAccount().getId(), id);
    }

    @PostMapping("/feedback")
    @ResponseStatus(HttpStatus.CREATED)
    public FeedbackResponse sendFeedback(
            @AuthenticationPrincipal PrincipalDetails principal,
            @Valid @RequestBody SendFeedbackRequest request) {
        return mailService.sendFeedback(principal.getAccount().getId(), request);
    }
}
