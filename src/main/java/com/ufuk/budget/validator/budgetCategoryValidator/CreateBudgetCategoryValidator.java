package com.ufuk.budget.validator.budgetCategoryValidator;

import com.ufuk.budget.exception.NotFoundException;
import com.ufuk.budget.model.request.CreateCategoryRequest;
import com.ufuk.budget.repository.BudgetCategoriesRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateBudgetCategoryValidator {
    private final BudgetCategoriesRepository budgetCategoriesRepository;

    public CreateBudgetCategoryValidator(BudgetCategoriesRepository budgetCategoriesRepository) {
        this.budgetCategoriesRepository = budgetCategoriesRepository;
    }

    public void validate(CreateCategoryRequest request) {
        boolean notExistByName = !budgetCategoriesRepository.existsByName(request.getName());
        if (notExistByName) {
            throw new NotFoundException("The category name you want to enter already exists " );
        }
    }


}
