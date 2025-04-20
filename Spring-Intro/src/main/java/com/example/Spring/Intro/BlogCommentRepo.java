package com.example.Spring.Intro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogCommentRepo extends JpaRepository<BlogComment, Long> {
}
