package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository3;

    public UserService(UserRepository userRepository3) {
        this.userRepository3 = userRepository3;
    }

    public User createUser(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userRepository3.save(user);
    }

    public void deleteUser(int userId){
        userRepository3.deleteById(userId);
    }

    public User updateUser(Integer id, String password){
        User user = userRepository3.findById(id).orElse(null);

        if(user == null) return null;

        user.setPassword(password);
        return userRepository3.save(user);
    }
}
