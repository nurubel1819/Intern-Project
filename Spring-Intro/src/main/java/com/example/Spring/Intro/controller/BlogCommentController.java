package com.example.Spring.Intro.controller;

import com.example.Spring.Intro.service.BlogCommentService;
import com.example.Spring.Intro.model.dto.BlogCommentDto;
import com.example.Spring.Intro.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class BlogCommentController {
    private final BlogCommentService blogCommentService;
    private final RoleService roleService;

    @PostMapping("/create")
    private ResponseEntity<String> upload(@RequestBody BlogCommentDto commentDto) {
        // Here also can all user. like admin,author,moderator,user
        // we not need to this code
        if(roleService.accessAuthority(commentDto.getUserId(),"ADMIN")
                || roleService.accessAuthority(commentDto.getUserId(),"AUTHOR")
                || roleService.accessAuthority(commentDto.getUserId(),"MODERATOR")
                || roleService.accessAuthority(commentDto.getUserId(),"USER"))
        {
            return new ResponseEntity<>(blogCommentService.addComment(commentDto), HttpStatus.OK);
        }
        else return new ResponseEntity<>("You don't have access to create blog", HttpStatus.FORBIDDEN);

    }
    @PatchMapping("/update")
    private ResponseEntity<String> update(@RequestBody BlogCommentDto commentDto) {
        if(roleService.accessAuthority(commentDto.getUserId(),"USER"))
        {
            return ResponseEntity.ok(blogCommentService.editComment(commentDto));
        }
        else return new ResponseEntity<>("You don't have access to create blog", HttpStatus.FORBIDDEN);
    }
    @GetMapping("/see/id={id}")
    private ResponseEntity<String> getAllComments(@PathVariable Long id) {
        // Here also can all user. like admin,author,moderator,user can see all comments
        return ResponseEntity.ok(blogCommentService.getComments(id));
    }

    @DeleteMapping("/delete_comment/user={userID}/comment={commentId}")
    private ResponseEntity<String> deleteComment(@PathVariable("userID") Long userID, @PathVariable Long commentId) {
        if(roleService.accessAuthority(userID,"USER")
        || roleService.accessAuthority(userID,"ADMIN"))
        {
            return ResponseEntity.ok(blogCommentService.deleteComment(commentId));
        }
        else return new ResponseEntity<>("You don't have access to create blog", HttpStatus.FORBIDDEN);
    }

}
