package com.example.lab5_asel.service.implem;


import com.example.lab5_asel.model.Category;
import com.example.lab5_asel.repository.CategoryRepository;
import com.example.lab5_asel.service.CategoryService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoriesRepository;

    // CONSTRUCTOR
    public CategoryServiceImpl(CategoryRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }


    // METHOD TO GET THE LIST OF ALL CATEGORIES
    @Override
    public List<Category> getAllCategories() {
        return categoriesRepository.findAllWithTasks();
    }


    @Override
    public Category getCategoryById(Long id) {
        return categoriesRepository.findByCategoryId(id);
    }


    // METHOD TO SAVE NEW CATEGORIES
    @Override
    public void saveCategories(Category categories) {
        this.categoriesRepository.save(categories);
    }


    // FIND AND GET CATEGORIES BY ID
    @Override
    public Category getCategoriesById(Long id) {
        Optional<Category> cat = categoriesRepository.findById(id);
        Category catn = null;
        if(cat.isPresent()) {
            catn = cat.get();
        } else {
            throw new RuntimeException("Categories not found for id " + id);
        }
        return catn;
    }


    // DELETE CATEGORY
    @Override
    public void deleteCategoryById(Long id) {
        this.categoriesRepository.deleteById(id);
    }
}



