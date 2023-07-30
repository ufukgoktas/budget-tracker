package com.ufuk.budget.validator.expenseValidator;

import com.ufuk.budget.exception.NotFoundException;
import com.ufuk.budget.model.request.CreateExpenseRequest;
import com.ufuk.budget.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateExpenseValidator {

    private final ExpenseRepository expenseRepository;

    public CreateExpenseValidator(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void validate(CreateExpenseRequest request) {
        boolean notExistByName = !expenseRepository.existsByName(request.getName());
        if (notExistByName == false) {
            throw new NotFoundException("Expense name already exist!");

        }
    }
}
