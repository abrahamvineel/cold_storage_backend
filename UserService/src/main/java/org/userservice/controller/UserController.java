package org.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.userservice.dto.UserCreationRequest;
import org.userservice.dto.UserLoginDetailsRequest;
import org.userservice.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserCreationRequest userCreationRequest) {
        userService.createUser(userCreationRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String loginUser(@RequestBody UserLoginDetailsRequest loginDetailsRequest) {
        userService.loginUser(loginDetailsRequest);
        return "Success";
    }
}
