package org.zisik.edu.note.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zisik.edu.exception.BookNoteNotFound;
import org.zisik.edu.exception.LibraryBookNotFound;
import org.zisik.edu.flow.domain.FlowSession;
import org.zisik.edu.flow.repository.FlowMemoRepository;
import org.zisik.edu.flow.repository.FlowSessionRepository;
import org.zisik.edu.flow.response.FlowMemoResponse;
import org.zisik.edu.library.repository.UserLibraryRepository;
import org.zisik.edu.note.domain.BookNote;
import org.zisik.edu.note.repository.BookNoteRepository;
import org.zisik.edu.note.request.CreateBookNoteRequest;
import org.zisik.edu.note.request.UpdateBookNoteRequest;
import org.zisik.edu.note.response.BookNoteResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookNoteService {

    private final BookNoteRepository bookNoteRepository;
    private final UserLibraryRepository userLibraryRepository;
    private final FlowSessionRepository flowSessionRepository;
    private final FlowMemoRepository flowMemoRepository;

    public BookNoteResponse getBookNote(Long accountId, Long userLibraryId) {
        // 서재 책 접근 권한 확인
        userLibraryRepository.findById(userLibraryId)
                .filter(ul -> ul.getAccountId().equals(accountId))
                .orElseThrow(LibraryBookNotFound::new);

        BookNote note = bookNoteRepository.findByUserLibraryId(userLibraryId)
                .orElseThrow(BookNoteNotFound::new);

        // 집중 모드 메모들 조회
        List<FlowMemoResponse> flowMemos = getFlowMemosForLibraryBook(accountId, userLibraryId);

        return BookNoteResponse.from(note, flowMemos);
    }

    @Transactional
    public BookNoteResponse createBookNote(Long accountId, Long userLibraryId, CreateBookNoteRequest request) {
        // 서재 책 접근 권한 확인
        userLibraryRepository.findById(userLibraryId)
                .filter(ul -> ul.getAccountId().equals(accountId))
                .orElseThrow(LibraryBookNotFound::new);

        // 이미 노트가 있으면 예외 또는 업데이트
        if (bookNoteRepository.existsByUserLibraryId(userLibraryId)) {
            BookNote existingNote = bookNoteRepository.findByUserLibraryId(userLibraryId).get();
            existingNote.updateContent(request.getContent());
            if (request.getNoteType() != null) {
                existingNote.updateNoteType(request.getNoteType());
            }
            return BookNoteResponse.from(existingNote);
        }

        BookNote note = BookNote.builder()
                .userLibraryId(userLibraryId)
                .noteType(request.getNoteType())
                .content(request.getContent())
                .build();

        bookNoteRepository.save(note);
        return BookNoteResponse.from(note);
    }

    @Transactional
    public BookNoteResponse updateBookNote(Long accountId, Long userLibraryId, UpdateBookNoteRequest request) {
        // 서재 책 접근 권한 확인
        userLibraryRepository.findById(userLibraryId)
                .filter(ul -> ul.getAccountId().equals(accountId))
                .orElseThrow(LibraryBookNotFound::new);

        BookNote note = bookNoteRepository.findByUserLibraryId(userLibraryId)
                .orElseThrow(BookNoteNotFound::new);

        if (request.getContent() != null) {
            note.updateContent(request.getContent());
        }
        if (request.getNoteType() != null) {
            note.updateNoteType(request.getNoteType());
        }

        List<FlowMemoResponse> flowMemos = getFlowMemosForLibraryBook(accountId, userLibraryId);
        return BookNoteResponse.from(note, flowMemos);
    }

    private List<FlowMemoResponse> getFlowMemosForLibraryBook(Long accountId, Long userLibraryId) {
        List<FlowSession> sessions = flowSessionRepository.findByAccountIdAndUserLibraryId(accountId, userLibraryId);

        return sessions.stream()
                .flatMap(session -> flowMemoRepository.findByFlowSessionId(session.getId()).stream())
                .map(FlowMemoResponse::from)
                .toList();
    }
}
