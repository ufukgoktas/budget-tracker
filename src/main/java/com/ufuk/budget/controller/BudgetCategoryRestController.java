package com.ufuk.budget.controller;

import com.ufuk.budget.model.dto.BudgetCategoriesDto;
import com.ufuk.budget.model.request.UpdateCategoryRequest;
import com.ufuk.budget.model.request.CreateCategoryRequest;
import com.ufuk.budget.service.BudgetCategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/budget/v1/budgetcategories")
public class BudgetCategoryRestController {
    private final BudgetCategoryService budgetCategoryService;

    public BudgetCategoryRestController(BudgetCategoryService budgetCategoryService) {
        this.budgetCategoryService = budgetCategoryService;
    }

    @GetMapping("/{categoriesId}")
    public BudgetCategoriesDto getCategoriesById(@PathVariable("categoriesId") Integer categoriesId) {
        return budgetCategoryService.getCategoriesById(categoriesId);
    }

    @PostMapping
    public void createCategory(@RequestBody CreateCategoryRequest request) {
        budgetCategoryService.createCategory(request);
    }

    @PutMapping
    public void updateCategory(@RequestBody UpdateCategoryRequest request) {
        budgetCategoryService.updateCategories(request);
    }

    @DeleteMapping("/{categoriesId}")
    public void deleteCategoryById(@PathVariable("categoriesId") Integer categoriesId) {
        budgetCategoryService.deleteCategoryById(categoriesId);
    }

}
