package org.zisik.edu.book.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zisik.edu.book.domain.Commit;

public interface CommitRepository extends JpaRepository<Commit, Long> {
}
