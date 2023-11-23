package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setPubDate(new Date());

        Optional<User> userOptional = userRepository1.findById(userId);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            blog.setUser(user);

            blogRepository1.save(blog);
            user.getBlogList().add(blog);
            userRepository1.save(user);
            return blog;
        }
        return null;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Optional<Blog> blogOptional = blogRepository1.findById(blogId);
        if(blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            blog.getImageList().clear();
        }
        blogRepository1.deleteById(blogId);
    }
}
