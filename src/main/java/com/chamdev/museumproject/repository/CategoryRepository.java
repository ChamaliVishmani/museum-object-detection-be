package com.chamdev.museumproject.repository;

import com.chamdev.museumproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);
}
