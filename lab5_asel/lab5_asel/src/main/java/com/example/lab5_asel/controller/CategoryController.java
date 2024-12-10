package com.example.lab5_asel.controller;


import com.example.lab5_asel.model.Category;
import com.example.lab5_asel.model.Users;
import com.example.lab5_asel.service.CategoryService;
import com.example.lab5_asel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    private final UserService usersService;

    //CONSTRUCTOR
    @Autowired
    public CategoryController(CategoryService categoryService, UserService usersService) {
        this.categoryService = categoryService;
        this.usersService = usersService;
    }



    //LIST OF CATEGORIES
    @GetMapping("/categories")
    public String getCategories(Model model, Principal principal) {
        String username = principal.getName();
        Users user = usersService.findByUsername(username);
        model.addAttribute("user", user);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("listCategories", categories);
        return "category";
    }



    //NEW CATEGORY
    @GetMapping("/showNewCategoryForm")
    public String showNewCategoryForm(Model model, Principal principal) {
        String username = principal.getName();
        Users user = usersService.findByUsername(username);
        model.addAttribute("user", user);
        Category category = new Category();
        model.addAttribute("category", category);
        return "addCategory";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.saveCategories(category);
        return "redirect:/categories";
    }



    //UPDATE
    @GetMapping("/showFormForUpdateCategory/{id}")
    public String showFormForUpdate(@PathVariable Long id, Model model, Principal principal) {
        String username = principal.getName();
        Users user = usersService.findByUsername(username);
        model.addAttribute("user", user);
        Category category = categoryService.getCategoriesById(id);
        model.addAttribute("category", category);
        return "updateCategory";
    }



    //DELETE
    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        this.categoryService.deleteCategoryById(id);
        return "redirect:/categories";
    }
}

