package com.LeBao.sales.controllers;

import com.LeBao.sales.models.Category;
import com.LeBao.sales.models.Product;
import com.LeBao.sales.models.User;
import com.LeBao.sales.repositories.CategoryRepository;
import com.LeBao.sales.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/addCategory")
    public ResponseEntity<String> addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return ResponseEntity.ok("Add a category successfully");
    }

    @GetMapping("/getCategoryById/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(categoryService.getCategoryById(categoryId));
    }

    @PostMapping("/updateCategory/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        categoryService.updateCategory(categoryId,category);
        return ResponseEntity.ok("Update this category successfully");
    }

    @PostMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Delete this category successfully");
    }
}
