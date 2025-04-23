package com.example.Spring.Intro.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User viewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @Column(nullable = false)
    private String content;

}