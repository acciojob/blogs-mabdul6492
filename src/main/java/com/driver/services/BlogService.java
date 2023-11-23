package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BlogService {
    BlogRepository blogRepository;
    UserRepository userRepository;

    public BlogService(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setPubDate(new Date());

        User user = userRepository.findById(userId).orElse(null);
        if(user == null) return blog;

        user.getBlogList().add(blog);
        userRepository.save(user);
        blog.setUser(user);
        blogRepository.save(blog);

        return blog;

    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if(blog == null) return;

        blog.getImageList().clear();
        blogRepository.deleteById(blogId);
    }
}
