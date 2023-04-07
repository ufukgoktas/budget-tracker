package com.ufuk.budget.service;

import com.ufuk.budget.entity.BudgetCategory;
import com.ufuk.budget.entity.User;
import com.ufuk.budget.model.dto.BudgetCategoriesDto;
import com.ufuk.budget.model.request.UpdateCategoryRequest;
import com.ufuk.budget.model.request.CreateCategoryRequest;
import com.ufuk.budget.repository.BudgetCategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BudgetCategoryService {
    private final BudgetCategoriesRepository budgetCategoriesRepository;
    private final UserService userService;

    public BudgetCategoryService(UserService userService, BudgetCategoriesRepository budgetCategoriesRepository) {
        this.budgetCategoriesRepository = budgetCategoriesRepository;
        this.userService = userService;
    }

    public BudgetCategoriesDto getCategoriesById(Integer budgetCategoriesId) {
        Optional<BudgetCategory> budgetCategoriesOptional = budgetCategoriesRepository.findById(budgetCategoriesId);
        if (budgetCategoriesOptional.isPresent()) {
            BudgetCategory budgetCategory = budgetCategoriesOptional.get();
            return new BudgetCategoriesDto(budgetCategory.getName(), budgetCategory.getAmount());
        }
        return new BudgetCategoriesDto();
    }

    public BudgetCategory getBudgetCategoriesId(Integer categoryId) {
        return budgetCategoriesRepository.findById(categoryId).orElseThrow(IllegalAccessError::new);
    }

    public void createCategory(CreateCategoryRequest request) {
        User user = userService.findUserById(request.getUserId());
        BudgetCategory categories = new BudgetCategory();
        categories.setName(request.getName());
        categories.setAmount(request.getAmount());
        categories.setUser(user);
        budgetCategoriesRepository.save(categories);
    }

    public void deleteCategoryById(Integer categoriesId) {
        budgetCategoriesRepository.deleteById(categoriesId);
    }

    public void updateCategories(UpdateCategoryRequest request) {
        Optional<BudgetCategory> budgetCategoriesOptional = budgetCategoriesRepository.findById(request.getCategoryId());
        if (budgetCategoriesOptional.isPresent()) {
            BudgetCategory categories = budgetCategoriesOptional.get();
            categories.setName(request.getName());
            categories.setAmount(request.getAmount());
            budgetCategoriesRepository.save(categories);
        } else {
            throw new IllegalStateException("Categories not found with ID: " + request.getCategoryId());
        }
    }

}


// Service (API & Web Service) -> DB (Postgresql)
//  1.Controller (İstekleri karşılayan yer)
//            - Request & Response & DTO (Data Transfer Object)
//  2.Service (İsteği alır ve business logic'e göre istenilen işlemi yapar)
//  3.Repository & DAO : Data Access Object (Database'e erişim sağladığımız yer. Select & Update & Insert)
//  4.Entity (Database'deki hangi tablo java'daki hangi object'e karşılık geldiği tanım.) (Object Relational Mapping)

// projedeki eksikler
// SSpring Exception Handlingpring Schema Validation
//  (Fırlattıgımız hataları düzgün bir şekilde yönetmek)
// Basic Authentication (Spring Security) Api'mizi korumak için (Diğer auth yöntemleri -> OAuth1 - OAuth3 - Bearer Token (JWT))
// Convertor

// araştırma konuları
// Solid prensipleri
// Clean code özetini oku (https://medium.com/@busrauzun/clean-code-kitabindan-notlar-1-temiz-kod-derken-44e6f7a27eb0)
// Design Patterns (refactoring.guru)
// Http Status Code'lar hakkında genel bilgi (200,201,202(başarılı responselarda)  400,401,403,404,405(request'te bir sıkıntı var)  500'lüler (hatalar için))
