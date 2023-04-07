package com.ufuk.budget.service;

import com.ufuk.budget.entity.BudgetCategory;
import com.ufuk.budget.entity.User;
import com.ufuk.budget.model.dto.BudgetCategoriesDto;
import com.ufuk.budget.model.request.UpdateCategoryRequest;
import com.ufuk.budget.model.request.CreateCategoryRequest;
import com.ufuk.budget.repository.BudgetCategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BudgetCategoryService {
    private final BudgetCategoriesRepository budgetCategoriesRepository;
    private final UserService userService;

    public BudgetCategoryService(UserService userService, BudgetCategoriesRepository budgetCategoriesRepository) {
        this.budgetCategoriesRepository = budgetCategoriesRepository;
        this.userService = userService;
    }

    public BudgetCategoriesDto getCategoriesById(Integer budgetCategoriesId) {
        Optional<BudgetCategory> budgetCategoriesOptional = budgetCategoriesRepository.findById(budgetCategoriesId);
        if (budgetCategoriesOptional.isPresent()) {
            BudgetCategory budgetCategory = budgetCategoriesOptional.get();
            return new BudgetCategoriesDto(budgetCategory.getName(), budgetCategory.getAmount());
        }
        return new BudgetCategoriesDto();
    }

    public BudgetCategory getBudgetCategoriesId(Integer categoryId) {
        return budgetCategoriesRepository.findById(categoryId).orElseThrow(IllegalAccessError::new);
    }

    public void createCategory(CreateCategoryRequest request) {
        User user = userService.findUserById(request.getUserId());
        BudgetCategory categories = new BudgetCategory();
        categories.setName(request.getName());
        categories.setAmount(request.getAmount());
        categories.setUser(user);
        budgetCategoriesRepository.save(categories);
    }

    public void deleteCategoryById(Integer categoriesId) {
        budgetCategoriesRepository.deleteById(categoriesId);
    }

    public void updateCategories(UpdateCategoryRequest request) {
        Optional<BudgetCategory> budgetCategoriesOptional = budgetCategoriesRepository.findById(request.getCategoryId());
        if (budgetCategoriesOptional.isPresent()) {
            BudgetCategory categories = budgetCategoriesOptional.get();
            categories.setName(request.getName());
            categories.setAmount(request.getAmount());
            budgetCategoriesRepository.save(categories);
        } else {
            throw new IllegalStateException("Categories not found with ID: " + request.getCategoryId());
        }
    }

}
