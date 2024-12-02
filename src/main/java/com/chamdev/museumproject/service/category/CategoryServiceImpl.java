package com.chamdev.museumproject.service.category;

import com.chamdev.museumproject.exceptions.AlreadyExistsException;
import com.chamdev.museumproject.repository.CategoryRepository;
import com.chamdev.museumproject.utils.enums.CategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public void addCategory(CategoryType categoryTypeName) {
        com.chamdev.museumproject.model.Category category =  categoryRepository.findByName(categoryTypeName);
        if(category != null) {
            throw new AlreadyExistsException("Category already exists");
        }
        categoryRepository.save(new com.chamdev.museumproject.model.Category(categoryTypeName));
    }
}
