package org.zisik.edu.note.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.zisik.edu.config.auth.PrincipalDetails;
import org.zisik.edu.note.request.CreateBookNoteRequest;
import org.zisik.edu.note.request.UpdateBookNoteRequest;
import org.zisik.edu.note.response.BookNoteResponse;
import org.zisik.edu.note.service.BookNoteService;

@RestController
@RequestMapping("/api/library/books")
@RequiredArgsConstructor
public class BookNoteController {

    private final BookNoteService bookNoteService;

    @GetMapping("/{id}/note")
    public BookNoteResponse getBookNote(
            @AuthenticationPrincipal PrincipalDetails principal,
            @PathVariable Long id) {
        return bookNoteService.getBookNote(principal.getAccount().getId(), id);
    }

    @PostMapping("/{id}/note")
    @ResponseStatus(HttpStatus.CREATED)
    public BookNoteResponse createBookNote(
            @AuthenticationPrincipal PrincipalDetails principal,
            @PathVariable Long id,
            @RequestBody CreateBookNoteRequest request) {
        return bookNoteService.createBookNote(principal.getAccount().getId(), id, request);
    }

    @PatchMapping("/{id}/note")
    public BookNoteResponse updateBookNote(
            @AuthenticationPrincipal PrincipalDetails principal,
            @PathVariable Long id,
            @RequestBody UpdateBookNoteRequest request) {
        return bookNoteService.updateBookNote(principal.getAccount().getId(), id, request);
    }
}
