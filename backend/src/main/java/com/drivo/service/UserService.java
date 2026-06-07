package com.drivo.service;

import com.drivo.dto.UserDto;
import com.drivo.entity.User;
import com.drivo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder
            passwordEncoder;

    @Autowired
    private JwtService jwtService;


    public User createUser(User user){

        user.setPassword(

                passwordEncoder.encode(
                        user.getPassword())

        );

        return userRepository.save(user);

    }

    public List<UserDto> getAllUsers() {

        return userRepository
                .findAll()
                .stream()
                .map(user ->

                        new UserDto(

                                user.getId(),

                                user.getName(),

                                user.getEmail(),

                                user.getRole().name()

                        )

                )
                .collect(
                        Collectors.toList()
                );

    }
    public String login(
            String email,
            String password) {

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        if(!passwordEncoder.matches(

                password,

                user.getPassword()

        )) {

            throw new RuntimeException(
                    "Invalid Password");
        }

        return jwtService
                .generateToken(

                        user.getEmail(),

                        user.getRole()
                                .name()

                );

    }


}