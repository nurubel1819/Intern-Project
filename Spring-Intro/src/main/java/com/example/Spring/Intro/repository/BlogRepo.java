package com.example.Spring.Intro.repository;

import com.example.Spring.Intro.model.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepo extends JpaRepository<Blog, Long> {
}
