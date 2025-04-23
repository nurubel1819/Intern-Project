package com.example.Spring.Intro.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {
    /*private Long id;
    private String title;
    private String content;*/

    @JsonProperty(namespace = "id")
    private Long id;

    @JsonProperty(value = "title",required = true,defaultValue = "Default Title")  //, access = JsonProperty.Access.WRITE_ONLY
    String title;

    @JsonProperty(value = "content",required = true,defaultValue = "Default Content")
    String content;

    @JsonProperty(namespace = "author_user_id")
    private Long authorUserId;

    @JsonProperty(namespace = "created_at")
    private LocalDateTime createdAt;

    @JsonProperty(namespace = "updated_att")
    private LocalDateTime updatedAt;

    /*@JsonProperty(namespace = "rating")
    private Double rating;*/
}
