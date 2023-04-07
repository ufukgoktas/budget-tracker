package com.ufuk.budget.model.dto;

public class BudgetCategoriesDto {
    private String name;
    private Double amount;

    public BudgetCategoriesDto() {
    }

    public BudgetCategoriesDto(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }
}
