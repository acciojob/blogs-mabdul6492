package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

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

        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()) return null;

        User user = userOptional.get();
        blog.setUser(user);
        blogRepository.save(blog);

        user.getBlogList().add(blog);
        userRepository.save(user);

        return blog;

    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if(!blogOptional.isPresent()) return;

        Blog blog = blogOptional.get();
        blog.getImageList().clear();
        blogRepository.deleteById(blogId);
    }
}
