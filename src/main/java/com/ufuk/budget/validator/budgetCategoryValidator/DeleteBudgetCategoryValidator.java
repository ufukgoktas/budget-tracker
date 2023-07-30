package com.ufuk.budget.validator.budgetCategoryValidator;

import com.ufuk.budget.exception.NotFoundException;
import com.ufuk.budget.repository.BudgetCategoriesRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteBudgetCategoryValidator {
    private final BudgetCategoriesRepository budgetCategoriesRepository;

    public DeleteBudgetCategoryValidator(BudgetCategoriesRepository budgetCategoriesRepository) {
        this.budgetCategoriesRepository = budgetCategoriesRepository;
    }


    public void validate(Integer categoryId) {
        boolean notExistById = !budgetCategoriesRepository.existsById(categoryId);
        if (notExistById) {
            throw new NotFoundException("Category Not Found!");
        }
    }
}
