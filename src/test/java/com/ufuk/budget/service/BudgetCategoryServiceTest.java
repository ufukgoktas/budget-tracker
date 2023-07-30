package com.ufuk.budget.service;

import com.ufuk.budget.entity.BudgetCategory;
import com.ufuk.budget.entity.User;
import com.ufuk.budget.exception.NotFoundException;
import com.ufuk.budget.model.dto.BudgetCategoriesDto;
import com.ufuk.budget.model.request.CreateCategoryRequest;
import com.ufuk.budget.model.request.UpdateCategoryRequest;
import com.ufuk.budget.repository.BudgetCategoriesRepository;
import com.ufuk.budget.repository.ExpenseRepository;
import com.ufuk.budget.validator.expenseValidator.CreateExpenseValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BudgetCategoryServiceTest {
    private  BudgetCategoriesRepository budgetCategoriesRepository;
    private  UserService userService;
    private  BudgetCategoryService budgetCategoryService;

    @BeforeEach
    public void setUp() {
        budgetCategoriesRepository = mock(BudgetCategoriesRepository.class);

        userService = mock(UserService.class);

        budgetCategoryService = new BudgetCategoryService(userService,budgetCategoriesRepository);
    }


    @Test
    public void testGetCategoriesById_ExistingCategory() {
        // Arrange
        Integer categoryId = 1;
        String categoryName = "Groceries";
        double categoryAmount = 200.0;
        BudgetCategory category = new BudgetCategory();
        category.setName(categoryName);
        category.setAmount(categoryAmount);
        category.setId(categoryId);
        when(budgetCategoriesRepository.findById(categoryId)).thenReturn(Optional.of(category));

        // Act
        BudgetCategoriesDto result = budgetCategoryService.getCategoriesById(categoryId);

        // Assert
        assertNotNull(result);
        assertEquals(categoryName, result.getName());
        assertEquals(categoryAmount, result.getAmount());
    }

    @Test
    public void testGetCategoriesById_NonExistingCategory() {
        // Arrange
        Integer categoryId = 5;
        when(budgetCategoriesRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act
        BudgetCategoriesDto result = budgetCategoryService.getCategoriesById(categoryId);

        // Assert
        assertNotNull(result);
        assertNull(result.getName());
        assertEquals(null, result.getAmount());
    }

    @Test
    public void testGetBudgetCategoriesId_ExistingCategory() {
        // Arrange
        Integer categoryId = 1;
        String categoryName = "Utilities";
        double categoryAmount = 150.0;

        BudgetCategory category = new BudgetCategory();
        category.setId(categoryId);
        category.setName(categoryName);
        category.setAmount(categoryAmount);
        when(budgetCategoriesRepository.findById(categoryId)).thenReturn(Optional.of(category));

        // Act
        BudgetCategory result = budgetCategoryService.getBudgetCategoriesId(categoryId);

        // Assert
        assertNotNull(result);
        assertEquals(categoryName, result.getName());
        assertEquals(categoryAmount, result.getAmount());
    }

    @Test
    public void testGetBudgetCategoriesId_NonExistingCategory() {
        // Arrange
        Integer categoryId = 1;
        when(budgetCategoriesRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalAccessError.class, () -> budgetCategoryService.getBudgetCategoriesId(categoryId));
    }

    @Test
    void createCategory() {
        // Hazırlık (Arrange)
        CreateCategoryRequest request = new CreateCategoryRequest();
        request.setName("Sample Category");
        request.setAmount(500.0);
        request.setUserId(1);

        User user = new User();
        user.setId(1);
        when(userService.findUserById(request.getUserId())).thenReturn(user);

        // Eylem (Act)
        budgetCategoryService.createCategory(request);

        // Doğrulama (Assert)
        verify(budgetCategoriesRepository, times(1)).save(any(BudgetCategory.class));
    }


    @Test
    void deleteCategoryById() {
        // Hazırlık (Arrange)
        int categoryId = 1;

        // Eylem (Act)
        budgetCategoryService.deleteCategoryById(categoryId);

        // Doğrulama (Assert)
        verify(budgetCategoriesRepository, times(1)).deleteById(categoryId);
    }

    @Test
    public void testUpdateCategories_CategoriesFound_ShouldUpdateCategories() {
        // Hazırlık (Arrange)
        UpdateCategoryRequest request = new UpdateCategoryRequest();
        Integer categoryId = 1;
        request.setCategoryId(categoryId);
        request.setName("Updated Category");
        request.setAmount(800.0);

        BudgetCategory existingCategory = new BudgetCategory();
        existingCategory.setId(categoryId);
        when(budgetCategoriesRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));

        // Eylem (Act)
        budgetCategoryService.updateCategories(request);

        // Doğrulama (Assert)
        verify(budgetCategoriesRepository, times(1)).save(existingCategory);
        assertEquals(request.getName(), existingCategory.getName());
        assertEquals(request.getAmount(), existingCategory.getAmount());
    }

    @Test
    public void testUpdateCategories_CategoriesNotFound_ShouldThrowNotFoundException() {
        // Hazırlık (Arrange)
        UpdateCategoryRequest request = new UpdateCategoryRequest();
        int categoryId = 1;
        request.setCategoryId(categoryId);
        request.setName("Updated Category");
        request.setAmount(800.0);

        when(budgetCategoriesRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Eylem ve Doğrulama (Act and Assert)
        assertThrows(NotFoundException.class, () -> budgetCategoryService.updateCategories(request));
    }
}