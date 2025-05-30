package com.example.Spring.Intro.model.mapper;

import com.example.Spring.Intro.model.dto.BlogDto;
import com.example.Spring.Intro.model.entity.Blog;

public class MapperBlogAndComment {
    public static Blog mapBlogDtoToBlogDto(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setId(blogDto.getId());
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        return blog;
    }
    public static BlogDto mapBlogToBlogDto(Blog blog) {
        BlogDto blogDto = new BlogDto();
        blogDto.setId(blog.getId());
        blogDto.setTitle(blog.getTitle());
        blogDto.setContent(blog.getContent());
        return blogDto;
    }
}
