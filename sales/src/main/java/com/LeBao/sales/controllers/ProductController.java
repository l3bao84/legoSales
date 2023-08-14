package com.LeBao.sales.controllers;

import com.LeBao.sales.models.Category;
import com.LeBao.sales.models.Product;
import com.LeBao.sales.repositories.CategoryRepository;
import com.LeBao.sales.repositories.ProductRepository;
import com.LeBao.sales.services.ProductService;
import com.LeBao.sales.services.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StorageService storageService;

    @GetMapping("/getProduct/{productId}")
    public String getProduct(ModelMap modelMap, @PathVariable Long productId) {
        List<Product> products = productRepository.findAll();
        Collections.reverse(products);
        List<Product> recommendedProducts = new ArrayList<>();
        List<Product> topPickProducts = new ArrayList<>();
        modelMap.addAttribute("allThemes", categoryRepository.findAll());
        List<Category> allCats = new ArrayList<>();
        allCats = categoryRepository.findAll();

        for (int i = 0; i < 12; i++) {
            recommendedProducts.add(products.get(i));
        }

        topPickProducts.add(productRepository.findById(1L).get());
        topPickProducts.add(productRepository.findById(14L).get());
        topPickProducts.add(productRepository.findById(15L).get());
        topPickProducts.add(productRepository.findById(6L).get());

        allCats.remove(0);
        List<Category> randomCat = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i <= 3; i++) {
            int randomPosition = random.nextInt(allCats.size());
            randomCat.add(allCats.get(randomPosition));
            allCats.remove(randomPosition);
        }
        modelMap.addAttribute("products", recommendedProducts);
        modelMap.addAttribute("topPickProducts", topPickProducts);
        modelMap.addAttribute("randomCat", randomCat);
        modelMap.addAttribute("productName", productRepository.findById(productId).get().getProductName());
        return "detailProduct";
    }


    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        String status = productService.addProduct(product);
        return ResponseEntity.ok(status);
    }


    @PostMapping("/addImage")
    public ResponseEntity<String> addImage(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = storageService.uploadFileToFileSystem(file);
        return ResponseEntity.ok(filePath);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable Long productId) throws IOException {
        byte[] imageData = storageService.downloadImageFromFileSystem(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @PostMapping("/updateProduct/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        productService.updateProduct(productId, product);
        return ResponseEntity.ok("Update successfully");
    }

    @PostMapping("/deleteProduct/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Delete successfully");
    }
}
