package com.LeBao.sales.controllers;

import com.LeBao.sales.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckoutController {


    @GetMapping("/handleCheckout")
    public String handleCheckout(@RequestParam("data") String data) {
        checkoutService.checkoutHandler(data);
        return "redirect:/checkout";
    }

    @GetMapping("/checkout")
    public String checkout(ModelMap modelMap) {
        modelMap.addAttribute("addresses", checkoutService.getShippingAddress());
        modelMap.addAttribute("items", checkoutService.getCartItem());
        return "checkout";
    }
    @Autowired
    private CheckoutService checkoutService;


}
