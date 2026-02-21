package org.zisik.edu.flow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zisik.edu.exception.FlowSessionNotFound;
import org.zisik.edu.exception.LibraryBookNotFound;
import org.zisik.edu.flow.domain.FlowMemo;
import org.zisik.edu.flow.domain.FlowSession;
import org.zisik.edu.flow.repository.FlowMemoRepository;
import org.zisik.edu.flow.repository.FlowSessionRepository;
import org.zisik.edu.flow.request.CreateFlowMemoRequest;
import org.zisik.edu.flow.request.EndFlowSessionRequest;
import org.zisik.edu.flow.request.StartFlowSessionRequest;
import org.zisik.edu.flow.response.FlowMemoResponse;
import org.zisik.edu.flow.response.FlowSessionResponse;
import org.zisik.edu.library.domain.UserLibrary;
import org.zisik.edu.library.repository.UserLibraryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FlowService {

    private final FlowSessionRepository flowSessionRepository;
    private final FlowMemoRepository flowMemoRepository;
    private final UserLibraryRepository userLibraryRepository;

    @Transactional
    public FlowSessionResponse startSession(Long accountId, StartFlowSessionRequest request) {
        // 서재에 등록된 책인지 확인
        UserLibrary userLibrary = userLibraryRepository.findById(request.getUserLibraryId())
                .filter(ul -> ul.getAccountId().equals(accountId))
                .orElseThrow(LibraryBookNotFound::new);

        // 현재 페이지가 없으면 서재의 현재 페이지 사용
        Integer startPage = request.getStartPage();
        if (startPage == null) {
            startPage = userLibrary.getCurrentPage();
        }

        FlowSession session = FlowSession.builder()
                .accountId(accountId)
                .userLibraryId(request.getUserLibraryId())
                .startPage(startPage)
                .videoUrl(request.getVideoUrl())
                .build();

        flowSessionRepository.save(session);
        return FlowSessionResponse.from(session);
    }

    @Transactional
    public FlowSessionResponse endSession(Long accountId, Long sessionId, EndFlowSessionRequest request) {
        FlowSession session = flowSessionRepository.findById(sessionId)
                .filter(s -> s.getAccountId().equals(accountId))
                .orElseThrow(FlowSessionNotFound::new);

        session.endSession(request.getEndPage());

        // 서재의 현재 페이지 업데이트 (책갈피)
        if (request.getEndPage() != null) {
            userLibraryRepository.findById(session.getUserLibraryId())
                    .ifPresent(ul -> ul.updateCurrentPage(request.getEndPage()));
        }

        return FlowSessionResponse.from(session);
    }

    @Transactional
    public FlowMemoResponse createMemo(Long accountId, Long sessionId, CreateFlowMemoRequest request) {
        FlowSession session = flowSessionRepository.findById(sessionId)
                .filter(s -> s.getAccountId().equals(accountId))
                .orElseThrow(FlowSessionNotFound::new);

        FlowMemo memo = FlowMemo.builder()
                .flowSessionId(sessionId)
                .content(request.getContent())
                .pageNumber(request.getPageNumber())
                .build();

        flowMemoRepository.save(memo);
        return FlowMemoResponse.from(memo);
    }

    public List<FlowMemoResponse> getMemos(Long accountId, Long sessionId) {
        FlowSession session = flowSessionRepository.findById(sessionId)
                .filter(s -> s.getAccountId().equals(accountId))
                .orElseThrow(FlowSessionNotFound::new);

        return flowMemoRepository.findByFlowSessionIdOrderByCreatedAtAsc(sessionId)
                .stream()
                .map(FlowMemoResponse::from)
                .toList();
    }

    public FlowSessionResponse getActiveSession(Long accountId) {
        return flowSessionRepository.findActiveSession(accountId)
                .map(FlowSessionResponse::from)
                .orElse(null);
    }

    public FlowSessionResponse getSession(Long accountId, Long sessionId) {
        return flowSessionRepository.findById(sessionId)
                .filter(s -> s.getAccountId().equals(accountId))
                .map(FlowSessionResponse::from)
                .orElseThrow(FlowSessionNotFound::new);
    }
}
