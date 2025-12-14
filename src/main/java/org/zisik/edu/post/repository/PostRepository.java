package org.zisik.edu.post.repository;

import org.zisik.edu.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> , PostRepositoryCustom {
}
