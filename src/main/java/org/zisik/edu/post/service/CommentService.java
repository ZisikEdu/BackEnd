package org.zisik.edu.post.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.zisik.edu.exception.CommentNotFound;
import org.zisik.edu.exception.InvalidPassword;
import org.zisik.edu.exception.PostNotFound;
import org.zisik.edu.post.domain.Comment;
import org.zisik.edu.post.domain.Post;
import org.zisik.edu.post.repository.CommentRepository;
import org.zisik.edu.post.repository.PostRepository;
import org.zisik.edu.post.request.CommentCreate;
import org.zisik.edu.post.request.CommentDelete;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void write(Long postId, CommentCreate request) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFound());

        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        Comment comment = Comment.builder()
                .author(request.getAuthor())
                .password(encryptedPassword)
                .content(request.getContent())
                .build();

        post.addComment(comment);
    }

    public void delete(Long commentId, CommentDelete request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFound::new);

        String encryptedPassword = comment.getPassword();

        if (!passwordEncoder.matches(request.getPassword(), encryptedPassword)) {
            throw new InvalidPassword();
        }

        commentRepository.delete(comment);
    }
}
