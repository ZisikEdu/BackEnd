package org.zisik.edu.flow.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.zisik.edu.config.auth.PrincipalDetails;
import org.zisik.edu.flow.request.CreateFlowMemoRequest;
import org.zisik.edu.flow.request.EndFlowSessionRequest;
import org.zisik.edu.flow.request.StartFlowSessionRequest;
import org.zisik.edu.flow.response.FlowMemoResponse;
import org.zisik.edu.flow.response.FlowSessionResponse;
import org.zisik.edu.flow.service.FlowService;

import java.util.List;

@RestController
@RequestMapping("/api/flow")
@RequiredArgsConstructor
public class FlowController {

    private final FlowService flowService;

    @PostMapping("/sessions/start")
    @ResponseStatus(HttpStatus.CREATED)
    public FlowSessionResponse startSession(
            @AuthenticationPrincipal PrincipalDetails principal,
            @Valid @RequestBody StartFlowSessionRequest request) {
        return flowService.startSession(principal.getAccount().getId(), request);
    }

    @PostMapping("/sessions/{id}/end")
    public FlowSessionResponse endSession(
            @AuthenticationPrincipal PrincipalDetails principal,
            @PathVariable Long id,
            @RequestBody EndFlowSessionRequest request) {
        return flowService.endSession(principal.getAccount().getId(), id, request);
    }

    @PostMapping("/sessions/{id}/memos")
    @ResponseStatus(HttpStatus.CREATED)
    public FlowMemoResponse createMemo(
            @AuthenticationPrincipal PrincipalDetails principal,
            @PathVariable Long id,
            @Valid @RequestBody CreateFlowMemoRequest request) {
        return flowService.createMemo(principal.getAccount().getId(), id, request);
    }

    @GetMapping("/sessions/{id}/memos")
    public List<FlowMemoResponse> getMemos(
            @AuthenticationPrincipal PrincipalDetails principal,
            @PathVariable Long id) {
        return flowService.getMemos(principal.getAccount().getId(), id);
    }

    @GetMapping("/sessions/active")
    public FlowSessionResponse getActiveSession(
            @AuthenticationPrincipal PrincipalDetails principal) {
        return flowService.getActiveSession(principal.getAccount().getId());
    }

    @GetMapping("/sessions/{id}")
    public FlowSessionResponse getSession(
            @AuthenticationPrincipal PrincipalDetails principal,
            @PathVariable Long id) {
        return flowService.getSession(principal.getAccount().getId(), id);
    }
}
