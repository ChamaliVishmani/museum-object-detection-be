package com.chamdev.museumproject.repository;

import com.chamdev.museumproject.model.Category;
import com.chamdev.museumproject.utils.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(CategoryType categoryName);
}
