package com.ufuk.budget.entity;

import javax.persistence.*;

@Entity(name = "budget_category")
@SequenceGenerator(name = "id_generator", sequenceName = "seq_budget_category", allocationSize = 1)
public class BudgetCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "amount",nullable = false)
    private Double amount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BudgetCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", userId=" + user +
                '}';
    }
}
