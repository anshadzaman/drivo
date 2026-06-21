package com.drivo.service;

import com.drivo.dto.UserDto;
import com.drivo.entity.User;
import com.drivo.enums.Role;
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


    public User createUser(
            User user
    ) {

        if (

                userRepository
                        .findByEmail(
                                user.getEmail()
                        )
                        .isPresent()

        ) {

            throw new RuntimeException(
                    "Email already exists"
            );

        }

        if (

                user.getRole()
                        == Role.ADMIN

        ) {

            throw new RuntimeException(
                    "Admin registration not allowed"
            );

        }

        user.setPassword(

                passwordEncoder.encode(
                        user.getPassword()
                )

        );

        return userRepository.save(
                user
        );

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

        return jwtService.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole().name()
        );

    }

    public long getDriverCount() {

        return userRepository
                .countByRole(
                        Role.DRIVER);

    }

    public long getShopCount() {

        return userRepository
                .countByRole(
                        Role.SHOP);

    }

    public long getUserCount() {

        return userRepository
                .count();

    }
    public User getUserByEmail(
            String email) {

        return userRepository
                .findByEmail(email)
                .orElseThrow();

    }
    public void deleteUser(
            Long id,
            String currentEmail) {

        User user =
                userRepository
                        .findById(id)
                        .orElseThrow();

        User currentUser =
                userRepository
                        .findByEmail(
                                currentEmail)
                        .orElseThrow();

        if (user.getId()
                .equals(
                        currentUser.getId())) {

            throw new RuntimeException(
                    "You cannot delete your own account");

        }

        if (user.getRole() == Role.ADMIN
                &&
                userRepository.countByRole(
                        Role.ADMIN) == 1) {

            throw new RuntimeException(
                    "Cannot delete last admin");

        }

        userRepository.deleteById(id);

    }
}