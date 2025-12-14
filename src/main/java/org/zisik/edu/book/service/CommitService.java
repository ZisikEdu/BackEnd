package org.zisik.edu.book.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zisik.edu.book.domain.Commit;
import org.zisik.edu.book.repositroy.CommitRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommitService {
    private final CommitRepository commitRepository;

    public void addCommit(Long userId, Commit commit) {
        commitRepository.save(commit);
    }
}
