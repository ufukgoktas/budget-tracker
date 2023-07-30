package com.ufuk.budget.controller;

import com.ufuk.budget.model.dto.ExpenseDto;
import com.ufuk.budget.model.request.CreateExpenseRequest;
import com.ufuk.budget.model.request.UpdateExpenseRequest;
import com.ufuk.budget.service.ExpenseService;
import com.ufuk.budget.validator.expenseValidator.CreateExpenseValidator;
import com.ufuk.budget.validator.expenseValidator.DeleteExpenseValidator;
import com.ufuk.budget.validator.expenseValidator.UpdateExpenseValidator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/budget/v1/expense")
public class ExpenseRestController {
    private final ExpenseService expenseService;
    private final CreateExpenseValidator createExpenseValidator;
    private final DeleteExpenseValidator deleteExpenseValidator;
    private final UpdateExpenseValidator updateExpenseValidator;

    public ExpenseRestController(ExpenseService expenseService, CreateExpenseValidator createExpenseValidator, DeleteExpenseValidator deleteExpenseValidator, UpdateExpenseValidator updateExpenseValidator) {
        this.expenseService = expenseService;
        this.createExpenseValidator = createExpenseValidator;
        this.deleteExpenseValidator = deleteExpenseValidator;
        this.updateExpenseValidator = updateExpenseValidator;
    }

    @GetMapping("/{expenseId}")
    public ExpenseDto getExpenseById(@PathVariable("expenseId") Integer expenseId) {
        return expenseService.getExpenseById(expenseId);
    }

    @PostMapping
    public void createExpense(@RequestBody CreateExpenseRequest request) {

        createExpenseValidator.validate(request);
        expenseService.createExpense(request);
    }

    @DeleteMapping("/{expenseId}")
    public void deleteExpenseById(@PathVariable("expenseId") Integer expenseId) {
        deleteExpenseValidator.validate(expenseId);
        expenseService.deleteExpenseById(expenseId);
    }

    @PutMapping
    public void updateExpense(@RequestBody UpdateExpenseRequest request) {
        updateExpenseValidator.validate(request);
        expenseService.updateExpenseById(request);
    }

}
