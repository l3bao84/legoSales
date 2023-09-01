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
        return "home";
    }

    @GetMapping("/search")
    public String searchResultPage(ModelMap modelMap,
                                   @RequestParam("search") String keyword,
                                   @RequestParam(defaultValue = "0", required = false) int page,
                                   @RequestParam(value = "sort", required = false) String sortValue) {

        if(sortValue != null) {
            Page<Product> productPage = homeService.searchProducts(keyword,sortValue,page);
//            List<Product> sortedList = productPage.getContent();
//            if(sortValue.equalsIgnoreCase("Price: Low to High")) {
//                Collections.sort(sortedList, Comparator.comparing(Product::getPrice));
//            }else if(sortValue.equalsIgnoreCase("Price: High to Low")) {
//                Collections.sort(sortedList, Comparator.comparing(Product::getPrice).reversed());
//            }else if(sortValue.equalsIgnoreCase("A-Z")) {
//                Collections.sort(sortedList, Comparator.comparing(Product::getProductName));
//            }
            modelMap.addAttribute("allCats", categoryRepository.findAll());
            modelMap.addAttribute("products", productPage.getContent());
            modelMap.addAttribute("keyword", keyword);
            modelMap.addAttribute("allThemes", categoryRepository.findAll());
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("totalPages", productPage.getTotalPages());
            return "searchResult";
        }
        Page<Product> productPage = homeService.searchProducts(keyword,"",page);

        modelMap.addAttribute("allCats", categoryRepository.findAll());
        modelMap.addAttribute("products", productPage.getContent());
        modelMap.addAttribute("keyword", keyword);
        modelMap.addAttribute("allThemes", categoryRepository.findAll());
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("totalPages", productPage.getTotalPages());
        return "searchResult";
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystemByFileName(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImageFromFileSystemByFileName(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
