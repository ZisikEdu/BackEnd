package org.zisik.edu.flow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zisik.edu.flow.domain.FlowMemo;

import java.util.List;

public interface FlowMemoRepository extends JpaRepository<FlowMemo, Long> {

    List<FlowMemo> findByFlowSessionIdOrderByCreatedAtAsc(Long flowSessionId);

    List<FlowMemo> findByFlowSessionId(Long flowSessionId);
}
