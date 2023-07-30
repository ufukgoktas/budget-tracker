package com.ufuk.budget.model.request;

public class CreateCategoryRequest {
    private String name;
    private Double amount;
    private Integer userId;

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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
