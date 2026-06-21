package com.drivo.controller;

import com.drivo.dto.LoginRequest;
import com.drivo.dto.LoginResponse;
import com.drivo.dto.UserDto;
import com.drivo.entity.User;
import com.drivo.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService
    ) {

        this.userService =
                userService;

    }

    @PostMapping
    public ResponseEntity<String>
    createUser(

            @Valid
            @RequestBody
            User user

    ) {

        userService.createUser(
                user
        );

        return ResponseEntity.ok(
                "User created successfully"
        );

    }

    @GetMapping
    public List<UserDto>
    getAllUsers() {

        return userService
                .getAllUsers();

    }

    @PostMapping("/login")
    public LoginResponse login(

            @Valid
            @RequestBody
            LoginRequest request

    ) {

        return new LoginResponse(

                userService.login(

                        request.getEmail(),

                        request.getPassword()

                )

        );

    }

    @GetMapping("/count")
    public long getUserCount() {

        return userService
                .getUserCount();

    }

    @GetMapping("/count/drivers")
    public long getDriverCount() {

        return userService
                .getDriverCount();

    }

    @GetMapping("/count/shops")
    public long getShopCount() {

        return userService
                .getShopCount();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteUser(

            @PathVariable
            Long id

    ) {

        String email =

                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        userService.deleteUser(
                id,
                email
        );

        return ResponseEntity.ok(
                "User deleted successfully"
        );

    }

}