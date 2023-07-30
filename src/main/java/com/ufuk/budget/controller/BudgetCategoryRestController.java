package com.ufuk.budget.controller;

import com.ufuk.budget.model.dto.BudgetCategoriesDto;
import com.ufuk.budget.model.request.UpdateCategoryRequest;
import com.ufuk.budget.model.request.CreateCategoryRequest;
import com.ufuk.budget.service.BudgetCategoryService;
import com.ufuk.budget.validator.budgetCategoryValidator.CreateBudgetCategoryValidator;
import com.ufuk.budget.validator.budgetCategoryValidator.DeleteBudgetCategoryValidator;
import com.ufuk.budget.validator.budgetCategoryValidator.UpdateBudgetCategoryValidator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/budget/v1/budgetcategories")
public class BudgetCategoryRestController {
    private final BudgetCategoryService budgetCategoryService;
    private final CreateBudgetCategoryValidator budgetCategoryValidator;
    private final UpdateBudgetCategoryValidator updateBudgetCategoryValidator;
    private final DeleteBudgetCategoryValidator deleteBudgetCategoryValidator;

    public BudgetCategoryRestController(BudgetCategoryService budgetCategoryService, CreateBudgetCategoryValidator budgetCategoryValidator, UpdateBudgetCategoryValidator updateBudgetCategoryValidator, DeleteBudgetCategoryValidator deleteBudgetCategoryValidator) {
        this.budgetCategoryService = budgetCategoryService;
        this.budgetCategoryValidator = budgetCategoryValidator;
        this.updateBudgetCategoryValidator = updateBudgetCategoryValidator;
        this.deleteBudgetCategoryValidator = deleteBudgetCategoryValidator;
    }

    @GetMapping("/{categoriesId}")
    public BudgetCategoriesDto getCategoriesById(@PathVariable("categoriesId") Integer categoriesId) {
        return budgetCategoryService.getCategoriesById(categoriesId);
    }

    @PostMapping
    public void createCategory(@RequestBody CreateCategoryRequest request) {
        budgetCategoryValidator.validate(request);
        budgetCategoryService.createCategory(request);
    }

    @PutMapping
    public void updateCategory(@RequestBody UpdateCategoryRequest request) {
        updateBudgetCategoryValidator.validate(request);
        budgetCategoryService.updateCategories(request);
    }

    @DeleteMapping("/{categoriesId}")
    public void deleteCategoryById(@PathVariable("categoriesId") Integer categoryId) {
        deleteBudgetCategoryValidator.validate(categoryId);
        budgetCategoryService.deleteCategoryById(categoryId);
    }

}
