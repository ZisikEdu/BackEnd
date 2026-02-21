package org.zisik.edu.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zisik.edu.book.domain.Book;
import org.zisik.edu.book.repositroy.BookRepository;
import org.zisik.edu.exception.AlreadyInLibrary;
import org.zisik.edu.exception.BookNotFound;
import org.zisik.edu.exception.LibraryBookNotFound;
import org.zisik.edu.library.domain.ReadingStatus;
import org.zisik.edu.library.domain.UserLibrary;
import org.zisik.edu.library.repository.UserLibraryRepository;
import org.zisik.edu.library.request.AddBookToLibraryRequest;
import org.zisik.edu.library.request.UpdateLibraryBookRequest;
import org.zisik.edu.library.response.UserLibraryResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LibraryService {

    private final UserLibraryRepository userLibraryRepository;
    private final BookRepository bookRepository;

    @Transactional
    public UserLibraryResponse addBookToLibrary(Long accountId, AddBookToLibraryRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(BookNotFound::new);

        if (userLibraryRepository.existsByAccountIdAndBookId(accountId, request.getBookId())) {
            throw new AlreadyInLibrary();
        }

        UserLibrary userLibrary = UserLibrary.builder()
                .accountId(accountId)
                .bookId(request.getBookId())
                .status(request.getStatus())
                .build();

        userLibraryRepository.save(userLibrary);
        return UserLibraryResponse.from(userLibrary, book);
    }

    public Page<UserLibraryResponse> getLibraryBooks(Long accountId, ReadingStatus status, Pageable pageable) {
        Page<UserLibrary> libraries;

        if (status != null) {
            libraries = userLibraryRepository.findByAccountIdAndStatus(accountId, status, pageable);
        } else {
            libraries = userLibraryRepository.findByAccountId(accountId, pageable);
        }

        return libraries.map(ul -> {
            Book book = bookRepository.findById(ul.getBookId())
                    .orElseThrow(BookNotFound::new);
            return UserLibraryResponse.from(ul, book);
        });
    }

    public UserLibraryResponse getLibraryBook(Long accountId, Long libraryBookId) {
        UserLibrary userLibrary = userLibraryRepository.findById(libraryBookId)
                .filter(ul -> ul.getAccountId().equals(accountId))
                .orElseThrow(LibraryBookNotFound::new);

        Book book = bookRepository.findById(userLibrary.getBookId())
                .orElseThrow(BookNotFound::new);

        return UserLibraryResponse.from(userLibrary, book);
    }

    @Transactional
    public UserLibraryResponse updateLibraryBook(Long accountId, Long libraryBookId, UpdateLibraryBookRequest request) {
        UserLibrary userLibrary = userLibraryRepository.findById(libraryBookId)
                .filter(ul -> ul.getAccountId().equals(accountId))
                .orElseThrow(LibraryBookNotFound::new);

        if (request.getStatus() != null) {
            userLibrary.updateStatus(request.getStatus());
        }
        if (request.getCurrentPage() != null) {
            userLibrary.updateCurrentPage(request.getCurrentPage());
        }
        if (request.getUserRating() != null) {
            userLibrary.updateUserRating(request.getUserRating());
        }

        Book book = bookRepository.findById(userLibrary.getBookId())
                .orElseThrow(BookNotFound::new);

        return UserLibraryResponse.from(userLibrary, book);
    }

    @Transactional
    public void removeFromLibrary(Long accountId, Long libraryBookId) {
        UserLibrary userLibrary = userLibraryRepository.findById(libraryBookId)
                .filter(ul -> ul.getAccountId().equals(accountId))
                .orElseThrow(LibraryBookNotFound::new);

        userLibraryRepository.delete(userLibrary);
    }

    @Transactional
    public UserLibraryResponse startReread(Long accountId, Long libraryBookId) {
        UserLibrary userLibrary = userLibraryRepository.findById(libraryBookId)
                .filter(ul -> ul.getAccountId().equals(accountId))
                .orElseThrow(LibraryBookNotFound::new);

        userLibrary.startReread();

        Book book = bookRepository.findById(userLibrary.getBookId())
                .orElseThrow(BookNotFound::new);

        return UserLibraryResponse.from(userLibrary, book);
    }

    public List<UserLibraryResponse> getCurrentlyReading(Long accountId) {
        return userLibraryRepository.findCurrentlyReading(accountId).stream()
                .map(ul -> {
                    Book book = bookRepository.findById(ul.getBookId())
                            .orElseThrow(BookNotFound::new);
                    return UserLibraryResponse.from(ul, book);
                })
                .toList();
    }
}
