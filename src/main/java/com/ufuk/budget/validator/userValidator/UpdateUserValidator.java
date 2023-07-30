package com.ufuk.budget.validator.userValidator;

import com.ufuk.budget.exception.NotFoundException;
import com.ufuk.budget.model.request.UpdateExpenseRequest;
import com.ufuk.budget.model.request.UpdateUserRequest;
import com.ufuk.budget.repository.ExpenseRepository;
import com.ufuk.budget.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserValidator {

    private final UserRepository userRepository;

    public UpdateUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void validate(UpdateUserRequest request){
        boolean notExistByEmail = !userRepository.existsByEmail(request.getEmail());
        if (notExistByEmail){
            throw new NotFoundException("Email is already exist!");
        }
    }
}
