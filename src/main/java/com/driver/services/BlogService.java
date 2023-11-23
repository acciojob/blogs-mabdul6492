package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.stereotype.Service;

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
        User user = userRepository.findById(userId).orElse(null);
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        if(user == null) return blog;
        user.getBlogList().add(blog);
        blog.setUser(user);

        return blogRepository.save(blog);

    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        blogRepository.deleteById(blogId);
    }
}
