package com.ufuk.budget.validator.expenseValidator;

import com.ufuk.budget.exception.NotFoundException;
import com.ufuk.budget.model.request.UpdateExpenseRequest;
import com.ufuk.budget.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateExpenseValidator {
   private final  ExpenseRepository expenseRepository;

    public UpdateExpenseValidator(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public void validate(UpdateExpenseRequest request){
        boolean notExistByName = !expenseRepository.existsByName(request.getName());
        if (notExistByName){
            throw new NotFoundException("Name is already exist!");
        }
    }
}
