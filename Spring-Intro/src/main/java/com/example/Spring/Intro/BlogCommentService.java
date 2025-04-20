package com.example.Spring.Intro;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogCommentService {
    BlogCommentRepo repo;
    UserRepo userRepo;

    public BlogCommentService(BlogCommentRepo repo, UserRepo userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public String addComment(BlogCommentDto commentDto, Long userId) {
        UserEntity user = userRepo.findById(userId).get();
        BlogComment comment = new BlogComment();
        comment.setContent(commentDto.getContent());
        comment.setViewer(user);
        BlogComment flag = repo.save(comment);
        if (flag != null) return "Comment added successfully";
        else return "Comment not added";
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

