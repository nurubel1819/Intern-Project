package com.example.Spring.Intro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class BlogCommentController {
    BlogCommentService blogCommentService;

    public BlogCommentController(BlogCommentService blogCommentService) {
        this.blogCommentService = blogCommentService;
    }

    @PostMapping("/upload/{id}")
    private ResponseEntity<String> upload(@RequestBody BlogCommentDto commentDto, @PathVariable Long id) {
        return new ResponseEntity<>(blogCommentService.addComment(commentDto,id), HttpStatus.OK);
    }
    @GetMapping("/see/{id}")
    private ResponseEntity<String> getAllComments(@PathVariable Long id) {
        return ResponseEntity.ok(blogCommentService.getComments(id));
    }

}
