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


    @PostMapping("/upload")
    private ResponseEntity<String> upload_new_blog(@RequestBody BlogDto blogDto) {
        if(roleService.access_authority(blogDto.getAuthorUserId(),"ADMIN")
        || roleService.access_authority(blogDto.getAuthorUserId(),"AUTHOR")
        || roleService.access_authority(blogDto.getAuthorUserId(),"MODERATOR"))
        {
            return new ResponseEntity<>(blogService.addBlog(blogDto), HttpStatus.OK);
        }
        //else return ResponseEntity.ok("You don't have access to create blog");
        else return new ResponseEntity<>("You don't have access to create blog", HttpStatus.FORBIDDEN);

    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> delete_blog(@PathVariable Long id) {
        if(roleService.access_authority(id,"ADMIN")
        || roleService.access_authority(id,"AUTHOR"))
        {
            return ResponseEntity.ok(blogService.deleteBlog(id));
        }
        else return new ResponseEntity<>("You don't have access to create blog", HttpStatus.FORBIDDEN);
    }
    @PutMapping("/update/{id}")
    private ResponseEntity<String> update_blog(@RequestBody BlogDto blogDto, @PathVariable Long id) {
        if(roleService.access_authority(blogDto.getAuthorUserId(),"ADMIN")
                || roleService.access_authority(blogDto.getAuthorUserId(),"AUTHOR")
                || roleService.access_authority(blogDto.getAuthorUserId(),"MODERATOR"))
        {
            return ResponseEntity.ok(blogService.updateBlog(blogDto,id));
        }
        else return new ResponseEntity<>("You don't have access to create blog", HttpStatus.FORBIDDEN);

    }
    @GetMapping("/get/{id}")
    private ResponseEntity<String> get_blog(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.get_blog_by_id(id));
    }
}
