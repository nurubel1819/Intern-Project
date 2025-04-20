package com.example.Spring.Intro;

import org.springframework.stereotype.Service;

@Service
public class BlogService {
    BlogRepo repo;
    UserRepo userRepo;

    public BlogService(BlogRepo repo, UserRepo userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public String addBlog(BlogDto blogDto, Long user_id) {
        UserEntity user = userRepo.findById(user_id).get();
        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        blog.setAuthor(user);
        Blog flag = repo.save(blog);
        if(flag != null) return "Upload blog successfully";
        else return "Something went wrong";
    }

    public String deleteBlog(Long blog_id) {
        if(repo.existsById(blog_id)) {
            repo.deleteById(blog_id);
            return "Delete blog successfully";
        }
        else return "This blog does not exist";
    }
    public String updateBlog(BlogDto blogDto, Long blog_id) {
        if(repo.existsById(blog_id)) {
            Blog blog = repo.findById(blog_id).get();
            blog.setTitle(blogDto.getTitle());
            blog.setContent(blogDto.getContent());
            repo.save(blog);
            return "Update blog successfully";
        }
        else return "This blog does not exist";
    }
    public String get_blog_by_id(Long blog_id) {
        if(repo.existsById(blog_id)) {
            String blog = repo.findById(blog_id).get().getTitle();
            String blog_content = repo.findById(blog_id).get().getContent();
            return blog +"\n"+ blog_content + "\n";
        }
        else return "This blog does not exist";
    }

}
