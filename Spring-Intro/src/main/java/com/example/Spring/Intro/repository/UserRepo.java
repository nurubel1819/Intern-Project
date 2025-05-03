package com.example.Spring.Intro.repository;

import com.example.Spring.Intro.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    //List<User> findAllBy(List<Long> ids);

    List<User> findAllByIdIn(List<Long> ids);
    User findByName(String name);
    //Optional<User> findByName(String name);

}
