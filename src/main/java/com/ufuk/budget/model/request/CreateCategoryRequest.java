package com.ufuk.budget.model.request;

public class CreateCategoryRequest {
    private String name;
    private Double amount;
    private Integer userId;

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getUserId() {
        return userId;
    }
}
