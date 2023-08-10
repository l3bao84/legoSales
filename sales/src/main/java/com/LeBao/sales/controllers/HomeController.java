package com.LeBao.sales.controllers;

import com.LeBao.sales.models.Category;
import com.LeBao.sales.models.Product;
import com.LeBao.sales.repositories.CategoryRepository;
import com.LeBao.sales.repositories.ProductRepository;
import com.LeBao.sales.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/home")
    public String hello(ModelMap modelMap) {
        List<Product> products = productRepository.findAll();
        Collections.reverse(products);
        List<Product> recommendedProducts = new ArrayList<>();
        List<Product> topPickProducts = new ArrayList<>();

        List<Category> randomCat = new ArrayList<>();
        randomCat = categoryRepository.findAll();

        for (int i = 0; i < 12; i++) {
            recommendedProducts.add(products.get(i));
        }

        topPickProducts.add(productRepository.findById(1L).get());
        topPickProducts.add(productRepository.findById(14L).get());
        topPickProducts.add(productRepository.findById(15L).get());
        topPickProducts.add(productRepository.findById(6L).get());

        randomCat.remove(0);
        modelMap.addAttribute("products", recommendedProducts);
        modelMap.addAttribute("topPickProducts", topPickProducts);
        modelMap.addAttribute("randomCat", randomCat);
        return "home";
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystemByFileName(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImageFromFileSystemByFileName(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
