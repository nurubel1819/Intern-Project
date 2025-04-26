package com.example.Spring.Intro.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCommentDto {
    private Long userId;
    @JsonProperty("blog_id")
    private Long blogId;
    private Long id;
    private String content;

}
