package com.ufuk.budget.service;

import com.ufuk.budget.entity.BudgetCategory;
import com.ufuk.budget.entity.Expense;
import com.ufuk.budget.exception.NotFoundException;
import com.ufuk.budget.model.dto.ExpenseDto;
import com.ufuk.budget.model.request.CreateExpenseRequest;
import com.ufuk.budget.model.request.UpdateExpenseRequest;
import com.ufuk.budget.repository.ExpenseRepository;
import com.ufuk.budget.validator.expenseValidator.CreateExpenseValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpenseServiceTest {

    private ExpenseRepository expenseRepository;
    private BudgetCategoryService budgetCategoryService;
    private CreateExpenseValidator createExpenseValidator;
    private ExpenseService expenseService;

    @BeforeEach
    public void setUp() {
        expenseRepository = mock(ExpenseRepository.class);
        budgetCategoryService = mock(BudgetCategoryService.class);
        createExpenseValidator = mock(CreateExpenseValidator.class);

        expenseService = new ExpenseService(expenseRepository, budgetCategoryService, createExpenseValidator);
    }

    @Test
    void testGetExpenseById() {
        int expenseId = 1;

        Expense expense = new Expense();
        expense.setAmount(10.0);
        expense.setName("test");
        expense.setDate(LocalDate.now());

        when(expenseRepository.findById(expenseId)).thenReturn(Optional.of(expense));

        ExpenseDto expenseDto = expenseService.getExpenseById(expenseId);

        assertEquals(expenseDto.getName(), "test");
        assertEquals(expenseDto.getAmount(), expense.getAmount());
        assertEquals(expenseDto.getDate(), expense.getDate());
    }
    @Test
    public void testGetExpenseById_ExpenseNotFound_ShouldThrowNotFoundException() {
        // Hazırlık (Arrange)
        int expenseId = 1;
        when(expenseRepository.findById(expenseId)).thenReturn(Optional.empty());

        // Eylem ve Doğrulama (Act and Assert)
        assertThrows(NotFoundException.class, () -> expenseService.getExpenseById(expenseId));
    }
@Test
    void testCreateExpense() {
        // Hazırlık (Arrange)
        CreateExpenseRequest request = new CreateExpenseRequest();
        request.setName("Sample Expense");
        request.setAmount(100.0);
        request.setDate(LocalDate.of(2023, 7, 30));
        request.setCategoryId(1);

        BudgetCategory budgetCategory = new BudgetCategory();
        budgetCategory.setId(1);
        when(budgetCategoryService.getBudgetCategoriesId(request.getCategoryId())).thenReturn(budgetCategory);

        // Eylem (Act)
        expenseService.createExpense(request);

        // Doğrulama (Assert)
        verify(expenseRepository, times(1)).save(any(Expense.class));
    }
    @Test
    public void testDeleteExpenseById() {
        // Hazırlık (Arrange)
        int expenseId = 1;

        // Eylem (Act)
        expenseService.deleteExpenseById(expenseId);

        // Doğrulama (Assert)
        verify(expenseRepository, times(1)).deleteById(expenseId);
    }
    @Test
    public void testUpdateExpenseById() {
        // Hazırlık (Arrange)
        UpdateExpenseRequest request = new UpdateExpenseRequest();
        int expenseId = 1;
        request.setExpenseId(expenseId);
        request.setName("Updated Expense");
        request.setAmount(200.0);
        request.setDate(LocalDate.of(2023, 8, 15));
        Expense existingExpense = new Expense();
        existingExpense.setId(expenseId);
        when(expenseRepository.findById(expenseId)).thenReturn(Optional.of(existingExpense));

        // Eylem (Act)
        expenseService.updateExpenseById(request);

        // Doğrulama (Assert)
        assertEquals(request.getName(), existingExpense.getName());
        assertEquals(request.getAmount(), existingExpense.getAmount());
        assertEquals(request.getDate(), existingExpense.getDate());
    }
    @Test
    public void testUpdateExpenseById_ExpenseNotFound_ShouldThrowNotFoundException() {
        // Hazırlık (Arrange)
        UpdateExpenseRequest request = new UpdateExpenseRequest();
        int expenseId = 1;
        request.setExpenseId(expenseId);
        request.setName("Updated Expense");
        request.setAmount(200.0);
        request.setDate(LocalDate.of(2023, 8, 15));
        when(expenseRepository.findById(expenseId)).thenReturn(Optional.empty());

        // Eylem ve Doğrulama (Act and Assert)
        assertThrows(NotFoundException.class, () -> expenseService.updateExpenseById(request));
    }
}