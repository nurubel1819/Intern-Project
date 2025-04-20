package com.example.Spring.Intro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCommentDto {
    private Long id;
    private String content;
}
