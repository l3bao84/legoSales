package com.LeBao.sales.services;

import com.LeBao.sales.entities.Category;
import com.LeBao.sales.entities.Product;
import com.LeBao.sales.repositories.CategoryRepository;
import com.LeBao.sales.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class HomeService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> searchProducts(String keyword, String sortValue, int page) {
//        return productRepository.findByProductNameContainingIgnoreCase(keyword, pageable);
        int pageSize = 8;
        Pageable pageable;
        if(sortValue.equalsIgnoreCase("Price: Low to High")) {
            pageable = PageRequest.of(page,pageSize,Sort.by(Sort.Order.asc("price")));
            return productRepository.findAll(pageable);
        }else if(sortValue.equalsIgnoreCase("Price: High to Low")) {
            pageable = PageRequest.of(page,pageSize,Sort.by(Sort.Order.desc("price")));
            return productRepository.findAll(pageable);
        }else if(sortValue.equalsIgnoreCase("A-Z")) {
            pageable = PageRequest.of(page,pageSize,Sort.by(Sort.Order.asc("productName")));
            return productRepository.findAll(pageable);
        }
        pageable = PageRequest.of(page, pageSize);
        return productRepository.findAll(pageable);
    }

    public List<Product> getRecommendedProducts() {
        List<Product> products = productRepository.findAll();
        Collections.reverse(products);
        List<Product> recommendedProducts = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            recommendedProducts.add(products.get(i));
        }
        return recommendedProducts;
    }

    public List<Product> getTopPickProducts() {
        List<Product> products = productRepository.findAll();
        List<Product> toppickProducts = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i <= 3; i++) {
            int randomPosition = random.nextInt(products.size());
            toppickProducts.add(products.get(randomPosition));
            products.remove(randomPosition);
        }
        return toppickProducts;
    }

    public List<Category> getRandomCategory() {
        List<Category> allCats = categoryRepository.findAll();
        allCats.remove(0);
        List<Category> randomCat = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i <= 3; i++) {
            int randomPosition = random.nextInt(allCats.size());
            randomCat.add(allCats.get(randomPosition));
            allCats.remove(randomPosition);
        }
        return randomCat;
    }
}
