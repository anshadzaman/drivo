package com.drivo.service;

import com.drivo.entity.User;
import com.drivo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){

        return userRepository.save(user);

    }

    public List<User> getAllUsers(){

        return userRepository.findAll();

    }

}