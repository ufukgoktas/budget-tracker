package com.ufuk.budget.validator.userValidator;

import com.ufuk.budget.exception.NotFoundException;
import com.ufuk.budget.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserValidator {

    private final UserRepository userRepository;

    public DeleteUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void validate(Integer userId) {
        boolean notExistById = !userRepository.existsById(userId);
        if (notExistById) {
            throw new NotFoundException("UserId Not Found!");
        }
    }
}
