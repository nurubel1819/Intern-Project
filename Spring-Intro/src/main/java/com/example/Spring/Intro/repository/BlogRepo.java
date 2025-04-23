package com.example.Spring.Intro.repository;

import com.example.Spring.Intro.model.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BlogRepo extends JpaRepository<Blog, Long> {
    //List<Blog> findByUser_Id(Long id);
}
