package com.LeBao.sales.services;

import com.LeBao.sales.entities.Category;
import com.LeBao.sales.entities.Product;
import com.LeBao.sales.repositories.CategoryRepository;
import com.LeBao.sales.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(Long categoryId, Category category) {
        Category foundCategory = categoryRepository.findById(categoryId).get();
        foundCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(foundCategory);
    }

    public void deleteCategory(Long categoryId) {
        List<Product> productList = productRepository.findAll();
        for (Product product:productList) {
            if(product.getCategory().getCategoryId() == categoryId) {
                product.setCategory(categoryRepository.findById(1L).get());
                categoryRepository.findById(1L).get().getProducts().add(product);
                productRepository.save(product);
                categoryRepository.save(categoryRepository.findById(1L).get());
            }
        }
        categoryRepository.deleteById(categoryId);
    }
}
