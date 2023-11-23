package com.driver.services;

import com.driver.models.User;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public User createUser(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName("test");
        user.setLastName("test");

        userRepository3.save(user);
        return user;
    }

    public void deleteUser(int userId){
        Optional<User> userOptional = userRepository3.findById(userId);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.getBlogList().clear();
        }
        userRepository3.deleteById(userId);
    }

    public User updateUser(Integer id, String password){
        Optional<User> userOptional = userRepository3.findById(id);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(password);
            userRepository3.save(user);
            return user;
        }
        return null;
    }
}
