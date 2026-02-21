package org.zisik.edu.post.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentDelete {

    private String password;

    public CommentDelete() {
    }

    @Builder
    public CommentDelete(String password) {
        this.password = password;
    }
}
