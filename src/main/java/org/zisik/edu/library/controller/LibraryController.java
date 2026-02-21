package org.zisik.edu.library.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.zisik.edu.config.auth.PrincipalDetails;
import org.zisik.edu.library.domain.ReadingStatus;
import org.zisik.edu.library.request.AddBookToLibraryRequest;
import org.zisik.edu.library.request.UpdateLibraryBookRequest;
import org.zisik.edu.library.response.UserLibraryResponse;
import org.zisik.edu.library.service.LibraryService;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public UserLibraryResponse addBookToLibrary(
            @AuthenticationPrincipal PrincipalDetails principal,
            @Valid @RequestBody AddBookToLibraryRequest request) {
        return libraryService.addBookToLibrary(principal.getAccount().getId(), request);
    }

    @GetMapping("/books")
    public Page<UserLibraryResponse> getLibraryBooks(
            @AuthenticationPrincipal PrincipalDetails principal,
            @RequestParam(required = false) ReadingStatus status,
            Pageable pageable) {
        return libraryService.getLibraryBooks(principal.getAccount().getId(), status, pageable);
    }

    @GetMapping("/books/{id}")
    public UserLibraryResponse getLibraryBook(
            @AuthenticationPrincipal PrincipalDetails principal,
            @PathVariable Long id) {
        return libraryService.getLibraryBook(principal.getAccount().getId(), id);
    }

    @PatchMapping("/books/{id}")
    public UserLibraryResponse updateLibraryBook(
            @AuthenticationPrincipal PrincipalDetails principal,
            @PathVariable Long id,
            @Valid @RequestBody UpdateLibraryBookRequest request) {
        return libraryService.updateLibraryBook(principal.getAccount().getId(), id, request);
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFromLibrary(
            @AuthenticationPrincipal PrincipalDetails principal,
            @PathVariable Long id) {
        libraryService.removeFromLibrary(principal.getAccount().getId(), id);
    }

    @PostMapping("/books/{id}/reread")
    public UserLibraryResponse startReread(
            @AuthenticationPrincipal PrincipalDetails principal,
            @PathVariable Long id) {
        return libraryService.startReread(principal.getAccount().getId(), id);
    }
}
