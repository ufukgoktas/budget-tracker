package com.ufuk.budget.service;

import com.ufuk.budget.entity.BudgetCategory;
import com.ufuk.budget.entity.Expense;
import com.ufuk.budget.model.dto.ExpenseDto;
import com.ufuk.budget.model.request.CreateExpenseRequest;
import com.ufuk.budget.model.request.UpdateExpenseRequest;
import com.ufuk.budget.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final BudgetCategoryService budgetCategoryService;

    public ExpenseService(ExpenseRepository expenseRepository, BudgetCategoryService budgetCategoryService) {
        this.expenseRepository = expenseRepository;
        this.budgetCategoryService = budgetCategoryService;
    }

    public ExpenseDto getExpenseById(Integer expenseId) {
        Optional<Expense> expensesOptional = expenseRepository.findById(expenseId);
        if (expensesOptional.isPresent()) {
            Expense expense = expensesOptional.get();
            return new ExpenseDto(expense.getName(), expense.getAmount(), expense.getDate());
        }

        return new ExpenseDto();
    }

    public void createExpense(CreateExpenseRequest request) {
        BudgetCategory budgetCategory = budgetCategoryService.getBudgetCategoriesId(request.getCategoryId());

        Expense expense = new Expense();
        expense.setName(request.getName());
        expense.setAmount(request.getAmount());
        expense.setDate(request.getDate());
        expense.setBudgetCategory(budgetCategory);
        expenseRepository.save(expense);
    }

    public void deleteExpenseById(Integer expensesId) {
        expenseRepository.deleteById(expensesId);
    }

    public void updateExpenseById(UpdateExpenseRequest request) {
        Optional<Expense> expensesOptional = expenseRepository.findById(request.getExpenseId());
        if (expensesOptional.isPresent()) {
            Expense expense = expensesOptional.get();
            expense.setName(request.getName());
            expense.setAmount(request.getAmount());
            expense.setDate(request.getDate());

        } else {
            throw new IllegalStateException("Expenses not found with ID: " + request.getExpenseId());
        }
    }
}
