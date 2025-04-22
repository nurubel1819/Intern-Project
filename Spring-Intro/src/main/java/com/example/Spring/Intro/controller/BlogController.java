package com.example.Spring.Intro.controller;

import com.example.Spring.Intro.service.BlogService;
import com.example.Spring.Intro.model.dto.BlogDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
public class BlogController {
    BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/upload/{id}")
    private ResponseEntity<String> upload_new_blog(@RequestBody BlogDto blogDto, @PathVariable Long id) {
        return new ResponseEntity<>(blogService.addBlog(blogDto,id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> delete_blog(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.deleteBlog(id));
    }
    @PutMapping("/update/{id}")
    private ResponseEntity<String> update_blog(@RequestBody BlogDto blogDto, @PathVariable Long id) {
        return ResponseEntity.ok(blogService.updateBlog(blogDto,id));
    }
    @GetMapping("/get/{id}")
    private ResponseEntity<String> get_blog(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.get_blog_by_id(id));
    }
}
