package com.example.Spring.Intro;


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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity viewer;

    @Column(nullable = false)
    private String content;

}