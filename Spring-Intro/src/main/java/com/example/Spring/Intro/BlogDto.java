package com.example.Spring.Intro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {
    private Long id;
    private String title;
    private String content;
}
