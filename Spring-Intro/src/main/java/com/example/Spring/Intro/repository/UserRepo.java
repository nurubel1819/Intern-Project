package com.example.Spring.Intro.repository;

import com.example.Spring.Intro.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

}
