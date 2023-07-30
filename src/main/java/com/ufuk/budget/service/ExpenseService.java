package com.ufuk.budget.service;

import com.ufuk.budget.entity.BudgetCategory;
import com.ufuk.budget.entity.Expense;
import com.ufuk.budget.model.dto.ExpenseDto;
import com.ufuk.budget.exception.NotFoundException;
import com.ufuk.budget.model.request.CreateExpenseRequest;
import com.ufuk.budget.model.request.UpdateExpenseRequest;
import com.ufuk.budget.repository.ExpenseRepository;
import com.ufuk.budget.validator.expenseValidator.CreateExpenseValidator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final BudgetCategoryService budgetCategoryService;
    private final CreateExpenseValidator createExpenseValidator;



    public ExpenseService(ExpenseRepository expenseRepository, BudgetCategoryService budgetCategoryService, CreateExpenseValidator createExpenseValidator){
        this.expenseRepository = expenseRepository;
        this.budgetCategoryService = budgetCategoryService;
        this.createExpenseValidator = createExpenseValidator;

    }

    public ExpenseDto getExpenseById(Integer expenseId) {


        Optional<Expense> expensesOptional = expenseRepository.findById(expenseId);
        if (expensesOptional.isPresent()) {
            Expense expense = expensesOptional.get();
            return new ExpenseDto(expense.getName(), expense.getAmount(), expense.getDate());
        }
        throw new NotFoundException("alolello");
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
        Expense expense = expenseRepository.findById(request.getExpenseId())
                .orElseThrow(() -> new NotFoundException("Expense not found with ID: " + request.getExpenseId()));

        expense.setName(request.getName());
        expense.setAmount(request.getAmount());
        expense.setDate(request.getDate());
    }

}
