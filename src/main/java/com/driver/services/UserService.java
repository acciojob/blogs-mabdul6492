package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public User createUser(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);

        return user;
    }

    public void deleteUser(int userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()) return;

        User user = userOptional.get();
        user.getBlogList().clear();
        userRepository.deleteById(userId);
    }

    public User updateUser(Integer id, String password){
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()) return null;

        User user = userOptional.get();
        user.setPassword(password);
        userRepository.save(user);

        return user;
    }
}
