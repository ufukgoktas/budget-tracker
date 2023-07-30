package com.ufuk.budget.validator.expenseValidator;

import com.ufuk.budget.exception.NotFoundException;
import com.ufuk.budget.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteExpenseValidator {
    private final ExpenseRepository expenseRepository;

    public DeleteExpenseValidator(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void validate(Integer expenseId) {
        boolean notExistById = !expenseRepository.existsById(expenseId);
        if (notExistById) {
            throw new NotFoundException("Expense Id Not Found!");
        }
    }
}
