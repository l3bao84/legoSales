package com.LeBao.sales.controllers;

import com.LeBao.sales.entities.Product;
import com.LeBao.sales.repositories.CategoryRepository;
import com.LeBao.sales.services.CartService;
import com.LeBao.sales.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CartService cartService;


    @GetMapping("/search")
    public String searchResultPage(ModelMap modelMap,
                                   @RequestParam("search") String keyword,
                                   @RequestParam(defaultValue = "0", required = false) int page,
                                   @RequestParam(value = "sort", required = false) String sortValue) {

        if(sortValue != null) {
            Page<Product> productPage = homeService.searchProducts(keyword,sortValue,page);
            modelMap.addAttribute("products", productPage.getContent());
            modelMap.addAttribute("totalPages", productPage.getTotalPages());
        }else {
            Page<Product> productPage = homeService.searchProducts(keyword,"",page);
            modelMap.addAttribute("products", productPage.getContent());
            modelMap.addAttribute("totalPages", productPage.getTotalPages());
        }
        modelMap.addAttribute("cartItemCount", cartService.getItemCart().size());
        modelMap.addAttribute("allCats", categoryRepository.findAll());
        modelMap.addAttribute("keyword", keyword);
        modelMap.addAttribute("allThemes", categoryRepository.findAll());
        modelMap.addAttribute("currentPage", page);
        return "searchResult";
    }
}
