package com.example.lab5_asel.service;

import com.example.lab5_asel.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();

    void saveCategories(Category categories);

    Category getCategoriesById(Long id);

    void deleteCategoryById(Long id);

    Category getCategoryById(Long id);
}
