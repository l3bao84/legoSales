package com.LeBao.sales.controllers;

import com.LeBao.sales.models.Category;
import com.LeBao.sales.models.Product;
import com.LeBao.sales.repositories.CategoryRepository;
import com.LeBao.sales.repositories.ProductRepository;
import com.LeBao.sales.services.HomeService;
import com.LeBao.sales.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private HomeService homeService;

    @GetMapping("/home")
    public String hello(ModelMap modelMap) {
        modelMap.addAttribute("allThemes", categoryRepository.findAll());
        modelMap.addAttribute("products", homeService.getRecommendedProducts());
        modelMap.addAttribute("topPickProducts", homeService.getTopPickProducts());
        modelMap.addAttribute("randomCat", homeService.getRandomCategory());
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
