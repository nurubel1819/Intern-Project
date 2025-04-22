package com.example.Spring.Intro.repository;

import com.example.Spring.Intro.model.entity.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogCommentRepo extends JpaRepository<BlogComment, Long> {
}
