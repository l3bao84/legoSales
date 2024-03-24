package com.LeBao.sales.controllers;

import com.LeBao.sales.DTO.ShippingAddressDTO;
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

    @ModelAttribute
    public void prepareDataForCart(ModelMap modelMap) {
        modelMap.addAttribute("allThemes", categoryRepository.findAll());
        modelMap.addAttribute("cartItemCount", cartService.getItemCart().size());
        modelMap.addAttribute("carts", cartService.getItemCart());
    }

    @GetMapping("")
    public String cart(ModelMap modelMap) {
        return "cart";
    }

    @PostMapping("/addItemToCart/{id}")
    public String addCartItem(ModelMap modelMap,
                          @PathVariable Long id,
                          @RequestParam("quantity") int quantity) {
        cartService.addItemToCart(id,quantity);
        return "redirect:/cart";
    }

    @PostMapping("/delAnCartItem/{id}")
    public String delCartItem(ModelMap modelMap, @PathVariable Long id) {
        cartService.delCartItem(id);
        return "redirect:/cart";
    }


}
