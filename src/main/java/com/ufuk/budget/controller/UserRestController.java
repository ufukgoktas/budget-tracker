package com.ufuk.budget.controller;

import com.ufuk.budget.model.dto.UserDto;
import com.ufuk.budget.model.request.CreateUserRequest;
import com.ufuk.budget.model.request.UpdateUserRequest;
import com.ufuk.budget.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/budget/v1/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
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
        userService.updateUser(request);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable("userId") Integer userId) {
        userService.deleteUserById(userId);
    }

}
