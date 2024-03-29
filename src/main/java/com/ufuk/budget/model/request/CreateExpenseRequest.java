package com.ufuk.budget.model.request;

import java.time.LocalDate;

public class CreateExpenseRequest {
    private String name;
    private Double amount;
    private LocalDate date;
    private Integer categoryId;

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
}
