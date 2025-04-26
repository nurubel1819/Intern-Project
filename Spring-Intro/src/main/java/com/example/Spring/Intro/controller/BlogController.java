package com.example.Spring.Intro.controller;

import com.example.Spring.Intro.service.BlogService;
import com.example.Spring.Intro.model.dto.BlogDto;
import com.example.Spring.Intro.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;
    private final RoleService roleService;


    @PostMapping("/create")
    private ResponseEntity<String> uploadNewBlog(@RequestBody BlogDto blogDto) {
        if(roleService.accessAuthority(blogDto.getAuthorUserId(),"ADMIN")
        || roleService.accessAuthority(blogDto.getAuthorUserId(),"AUTHOR")
        || roleService.accessAuthority(blogDto.getAuthorUserId(),"MODERATOR"))
        {
            return new ResponseEntity<>(blogService.addBlog(blogDto), HttpStatus.OK);
        }
        //else return ResponseEntity.ok("You don't have access to create blog");
        else return new ResponseEntity<>("You don't have access to create blog", HttpStatus.FORBIDDEN);

    }

    @DeleteMapping("/delete/user={userId}/comment={commentId}")
    private ResponseEntity<String> delete_blog(@PathVariable("userId") Long userId, @PathVariable Long commentId) {
        if(roleService.accessAuthority(userId,"ADMIN")
        || roleService.accessAuthority(userId,"AUTHOR"))
        {
            return ResponseEntity.ok(blogService.deleteBlog(commentId));
        }
        else return new ResponseEntity<>("You don't have access to create blog", HttpStatus.FORBIDDEN);
    }
    @PatchMapping("/update/id={id}")
    private ResponseEntity<String> update_blog(@RequestBody BlogDto blogDto, @PathVariable Long id) {
        if(roleService.accessAuthority(blogDto.getAuthorUserId(),"ADMIN")
                || roleService.accessAuthority(blogDto.getAuthorUserId(),"AUTHOR")
                || roleService.accessAuthority(blogDto.getAuthorUserId(),"MODERATOR"))
        {
            return ResponseEntity.ok(blogService.updateBlog(blogDto,id));
        }
        else return new ResponseEntity<>("You don't have access to create blog", HttpStatus.FORBIDDEN);

    }
    @GetMapping("/get/id={id}")
    private ResponseEntity<String> get_blog(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.get_blog_by_id(id));
    }
}
