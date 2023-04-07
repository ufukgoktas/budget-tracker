package com.ufuk.budget.model.dto;

import java.time.LocalDate;

public class ExpenseDto {
    private String name;
    private Double amount;

    private LocalDate date;

    public ExpenseDto() {
    }

    public ExpenseDto(String name, Double amount, LocalDate date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
