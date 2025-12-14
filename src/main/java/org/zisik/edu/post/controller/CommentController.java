package org.zisik.edu.post.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.zisik.edu.post.request.CommentCreate;
import org.zisik.edu.post.request.CommentDelete;
import org.springframework.web.bind.annotation.*;
import org.zisik.edu.post.service.CommentService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    private void write(@PathVariable Long postId, @RequestBody @Valid CommentCreate request){
        commentService.write(postId, request);
    }

    @PostMapping("/comments/{commentId}/delete")//delete requestBody 를 못넣는다
    private void delete(@PathVariable Long commentId, @RequestBody @Valid CommentDelete request) {
        commentService.delete(commentId, request);
    }
}
