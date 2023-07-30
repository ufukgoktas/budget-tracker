package com.ufuk.budget.controller;

import com.ufuk.budget.model.dto.UserDto;
import com.ufuk.budget.model.request.CreateUserRequest;
import com.ufuk.budget.model.request.UpdateUserRequest;
import com.ufuk.budget.service.UserService;
import com.ufuk.budget.validator.userValidator.DeleteUserValidator;
import com.ufuk.budget.validator.userValidator.UpdateUserValidator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/budget/v1/user")
public class UserRestController {

    private final UserService userService;
    private final DeleteUserValidator deleteUserValidator;
    private final UpdateUserValidator updateUserValidator;

    public UserRestController(UserService userService, DeleteUserValidator deleteUserValidator, UpdateUserValidator updateUserValidator) {
        this.userService = userService;
        this.deleteUserValidator = deleteUserValidator;
        this.updateUserValidator = updateUserValidator;
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public void createUser(@RequestBody CreateUserRequest request) {
        userService.createUser(request);
    }

    @PutMapping
    public void updateUser(@RequestBody UpdateUserRequest request) {
        updateUserValidator.validate(request);
        userService.updateUser(request);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable("userId") Integer userId) {
        deleteUserValidator.validate(userId);
        userService.deleteUserById(userId);
    }

}
