package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public void deleteUser(int userId){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) return;

        user.getBlogList().clear();
        userRepository.deleteById(userId);
    }

    public User updateUser(Integer id, String password){
        User user = userRepository.findById(id).orElse(null);

        if(user == null) return null;

        user.setPassword(password);
        return userRepository.save(user);
    }
}
