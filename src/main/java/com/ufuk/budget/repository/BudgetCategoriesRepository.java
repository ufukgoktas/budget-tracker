package com.ufuk.budget.repository;

import com.ufuk.budget.entity.BudgetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetCategoriesRepository extends JpaRepository<BudgetCategory, Integer> {
    boolean existsByName(String name);
}
