package com.example.Spring.Intro.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Blog> blogs;

    @OneToMany(mappedBy = "viewer")
    private List<BlogComment> comments;

    @ManyToMany(mappedBy = "user")
    private Set<UserRole> roles;

}
