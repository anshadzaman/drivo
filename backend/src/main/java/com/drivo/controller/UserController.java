package com.drivo.controller;

import com.drivo.dto.LoginResponse;
import com.drivo.dto.UserDto;
import com.drivo.entity.User;
import com.drivo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.drivo.dto.LoginRequest;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public User createUser(

            @Valid
            @RequestBody User user){

        return userService.createUser(user);

    }


    @GetMapping
    public List<UserDto> getAllUsers(){

        return userService.getAllUsers();

    }
    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody
            LoginRequest request) {

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

}