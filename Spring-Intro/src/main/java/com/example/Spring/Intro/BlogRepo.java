package com.example.Spring.Intro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepo extends JpaRepository<Blog, Long> {
}
