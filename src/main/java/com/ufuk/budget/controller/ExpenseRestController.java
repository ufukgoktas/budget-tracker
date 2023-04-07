package com.ufuk.budget.controller;

import com.ufuk.budget.model.dto.ExpenseDto;
import com.ufuk.budget.model.request.CreateExpenseRequest;
import com.ufuk.budget.model.request.UpdateExpenseRequest;
import com.ufuk.budget.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/budget/v1/expense")
public class ExpenseRestController {
    private final ExpenseService expenseService;

    public ExpenseRestController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/{expenseId}")
    public ExpenseDto getExpenseById(@PathVariable("expenseId") Integer expenseId) {
        return expenseService.getExpenseById(expenseId);
    }

    @PostMapping
    public void createExpense(@RequestBody CreateExpenseRequest request) {
        expenseService.createExpense(request);
    }

    @DeleteMapping("/{expenseId}")
    public void deleteExpenseById(@PathVariable("expenseId") Integer expenseId) {
        expenseService.deleteExpenseById(expenseId);
    }

    @PutMapping
    public void updateExpense(@RequestBody UpdateExpenseRequest request) {
        expenseService.updateExpenseById(request);
    }

}
