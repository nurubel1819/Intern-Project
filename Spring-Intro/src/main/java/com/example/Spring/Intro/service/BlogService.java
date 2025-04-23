package com.example.Spring.Intro.service;

import com.example.Spring.Intro.repository.BlogRepo;
import com.example.Spring.Intro.repository.UserRepo;
import com.example.Spring.Intro.model.dto.BlogDto;
import com.example.Spring.Intro.model.entity.Blog;
import com.example.Spring.Intro.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService {
    private final BlogRepo repo;
    private final UserRepo userRepo;

    public BlogService(BlogRepo repo, UserRepo userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public String addBlog(BlogDto blogDto) {



        Optional<User> user=userRepo.findById(blogDto.getAuthorUserId());
        if(user.isEmpty())
        {
            return null;
        }

        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        blog.setAuthor(user.get());
        Blog flag = repo.save(blog);
        if(flag != null) return "Upload blog successfully";
        else return "Something went wrong";

        //boolean flag = isCreateBlogAccessAuthority(blogDto.getAuthorUserId());
        /*if(!isCreateBlogAccessAuthority(blogDto.getAuthorUserId()))
        {
            return "You don't have access to create blog";
        }*/


        //User user = userRepo.findById(user_id).get();

        //return "save user";
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

    /*public List<Blog> getAllThisUserBlogs(Long id) {
        return repo.findByUser_Id(id);
    }*/

    private boolean isCreateBlogAccessAuthority(Long authorUserId) {
        User user = userRepo.findById(authorUserId).get();
        return user.getRoles().stream()
                        .anyMatch(role -> role.getRoleName().equalsIgnoreCase("ADMIN") || role.getRoleName().equalsIgnoreCase("MODERATOR")
                                || role.getRoleName().equalsIgnoreCase("Author"));
    }

}
