package com.example.Spring.Intro.controller;

import com.example.Spring.Intro.service.BlogCommentService;
import com.example.Spring.Intro.model.dto.BlogCommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class BlogCommentController {
    BlogCommentService blogCommentService;

    public BlogCommentController(BlogCommentService blogCommentService) {
        this.blogCommentService = blogCommentService;
    }

    @PostMapping("/upload")
    private ResponseEntity<String> upload(@RequestBody BlogCommentDto commentDto) {
        return new ResponseEntity<>(blogCommentService.addComment(commentDto), HttpStatus.OK);
    }
    @GetMapping("/see/{id}")
    private ResponseEntity<String> getAllComments(@PathVariable Long id) {
        return ResponseEntity.ok(blogCommentService.getComments(id));
    }

}
