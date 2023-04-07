package com.ufuk.budget.service;

import com.ufuk.budget.entity.User;
import com.ufuk.budget.model.dto.UserDto;
import com.ufuk.budget.model.request.CreateUserRequest;
import com.ufuk.budget.model.request.UpdateUserRequest;
import com.ufuk.budget.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserById(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getTotalBudget());
        }
        return new UserDto();
    }

    public User findUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
    }

    public void createUser(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(request.getPassword());

        userRepository.save(user);
    }

    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }

    public void updateUser(UpdateUserRequest request) {
        Optional<User> userOptional = userRepository.findById(request.getUserId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setTotalBudget(request.getTotalBudget());
            userRepository.save(user);
        } else {
            throw new IllegalStateException("User not found with ID: " + request.getUserId());
        }
    }

}
