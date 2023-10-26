package com.LeBao.sales.controllers;

import com.LeBao.sales.repositories.CategoryRepository;
import com.LeBao.sales.services.CartService;
import com.LeBao.sales.services.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private HomeService homeService;

    @GetMapping("")
    public String cart(ModelMap modelMap) {
        modelMap.addAttribute("products", homeService.getRecommendedProducts());
        modelMap.addAttribute("allThemes", categoryRepository.findAll());
        modelMap.addAttribute("cartItemCount", cartService.getItemCart().size());
        modelMap.addAttribute("carts", cartService.getItemCart());
        return "cart";
    }

    @PostMapping("/addItemToCart/{id}")
    public String addCartItem(ModelMap modelMap,
                          @PathVariable Long id,
                          @RequestParam("quantity") int quantity) {
        cartService.addItemToCart(id,quantity);
        modelMap.addAttribute("products", homeService.getRecommendedProducts());
        modelMap.addAttribute("allThemes", categoryRepository.findAll());
        modelMap.addAttribute("cartItemCount", cartService.getItemCart().size());
        modelMap.addAttribute("carts", cartService.getItemCart());
        return "redirect:/cart";
    }

    @PostMapping("/delAnCartItem/{id}")
    public String delCartItem(ModelMap modelMap, @PathVariable Long id) {
        cartService.delCartItem(id);
        modelMap.addAttribute("products", homeService.getRecommendedProducts());
        modelMap.addAttribute("allThemes", categoryRepository.findAll());
        modelMap.addAttribute("cartItemCount", cartService.getItemCart().size());
        modelMap.addAttribute("carts", cartService.getItemCart());
        return "redirect:/cart";
    }


}
