package com.example.Spring.Intro.service;

import com.example.Spring.Intro.model.entity.Blog;
import com.example.Spring.Intro.repository.BlogCommentRepo;
import com.example.Spring.Intro.repository.BlogRepo;
import com.example.Spring.Intro.repository.UserRepo;
import com.example.Spring.Intro.model.dto.BlogCommentDto;
import com.example.Spring.Intro.model.entity.BlogComment;
import com.example.Spring.Intro.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogCommentService {
    private final BlogCommentRepo repo;
    private final UserRepo userRepo;
    private final BlogRepo blogRepo;



    public String addComment(BlogCommentDto commentDto) {
        User user = userRepo.findById(commentDto.getUserId()).get();
        Blog blog = blogRepo.findById(commentDto.getBlogId()).get();
        BlogComment comment = new BlogComment();
        comment.setContent(commentDto.getContent());
        comment.setViewer(user);
        comment.setBlog(blog);
        BlogComment flag = repo.save(comment);
        if (flag != null) return "Comment added successfully";
        else return "Comment not added";
    }
    public String editComment(BlogCommentDto commentDto) {
        User user = userRepo.findById(commentDto.getUserId()).get();
        Blog blog = blogRepo.findById(commentDto.getBlogId()).get();
        BlogComment comment = repo.findById(commentDto.getId()).get();
        comment.setContent(commentDto.getContent());
        comment.setViewer(user);
        comment.setBlog(blog);
        BlogComment flag = repo.save(comment);
        if (flag != null) return "Comment edited successfully";
        else return "Comment not edited";
    }
    public String getComments(Long id) {
        return "Comment is : "+ repo.findById(id).get().getContent();
    }
    public String deleteComment(Long id) {
        if(repo.existsById(id)) {
            repo.deleteById(id);
            return "Comment deleted successfully";
        }
        else return "Comment not deleted";
    }
}

