package com.example.Spring.Intro.model.mapper;

import com.example.Spring.Intro.model.dto.BlogDto;
import com.example.Spring.Intro.model.entity.Blog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class BlogMapper {
    public Blog mapBlog(BlogDto blogDto)
    {
        Blog blog = new Blog();
        blog.setId(blogDto.getId());
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        blog.setContent(blogDto.getContent());
        return blog;
    }
}
