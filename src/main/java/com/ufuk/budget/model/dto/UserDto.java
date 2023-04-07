package com.ufuk.budget.model.dto;

public class UserDto {

    private Integer id;
    private String name;
    private String email;
    private Double totalBudget;

    public UserDto() {
    }

    public UserDto(Integer id, String name, String email, Double totalBudget) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.totalBudget = totalBudget;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }
}
