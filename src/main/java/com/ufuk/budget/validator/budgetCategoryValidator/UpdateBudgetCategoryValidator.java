package com.ufuk.budget.validator.budgetCategoryValidator;

import com.ufuk.budget.exception.NotFoundException;
import com.ufuk.budget.model.request.UpdateCategoryRequest;
import com.ufuk.budget.repository.BudgetCategoriesRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateBudgetCategoryValidator {
   private final BudgetCategoriesRepository budgetCategoriesRepository;


    public UpdateBudgetCategoryValidator(BudgetCategoriesRepository budgetCategoriesRepository) {
        this.budgetCategoriesRepository = budgetCategoriesRepository;
    }
    public void validate(UpdateCategoryRequest request){
        boolean notExistByName = !budgetCategoriesRepository.existsByName(request.getName());
        if (notExistByName){
            throw new NotFoundException("there is not category name that have " + request.getName());
        }
    }
}
