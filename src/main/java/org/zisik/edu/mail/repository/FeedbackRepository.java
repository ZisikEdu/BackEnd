package org.zisik.edu.mail.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zisik.edu.mail.domain.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    Page<Feedback> findBySenderId(Long senderId, Pageable pageable);
}
