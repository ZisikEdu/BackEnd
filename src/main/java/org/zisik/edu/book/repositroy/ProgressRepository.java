package org.zisik.edu.book.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zisik.edu.book.domain.Progress;
import org.zisik.edu.book.domain.ProgressId;

public interface ProgressRepository extends JpaRepository<Progress, ProgressId> {
}
