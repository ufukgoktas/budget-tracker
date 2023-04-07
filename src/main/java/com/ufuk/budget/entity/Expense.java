package com.ufuk.budget.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "expense")
@SequenceGenerator(name = "id_generator", sequenceName = "seq_expense", allocationSize = 1)

public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount",nullable = false)
    private Double amount = 0.0;

    @Column(name = "date",nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private BudgetCategory budgetCategory;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BudgetCategory getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(BudgetCategory categoryId) {
        this.budgetCategory = categoryId;
    }

    @Override
    public String toString() {
        return "expense{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", categoryId=" + budgetCategory +
                '}';
    }

}
