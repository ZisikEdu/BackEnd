package org.zisik.edu.post.repository;


import org.zisik.edu.post.domain.Post;
import org.zisik.edu.post.request.PostSearch;
import org.springframework.data.domain.Page;

public interface PostRepositoryCustom {

    Page<Post> getList(PostSearch postSearch);
}
