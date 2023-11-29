package com.LeBao.sales.controllers;

import com.LeBao.sales.DTO.ShippingAddressDTO;
import com.LeBao.sales.repositories.CategoryRepository;
import com.LeBao.sales.repositories.ProductRepository;
import com.LeBao.sales.services.AccountService;
import com.LeBao.sales.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/my-account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CartService cartService;

    @ModelAttribute
    public void prepareDataForMyAccount(ModelMap modelMap) {
        modelMap.addAttribute("allThemes", categoryRepository.findAll());
        modelMap.addAttribute("address", new ShippingAddressDTO());
        modelMap.addAttribute("addresses", accountService.getShippingAddress());
        modelMap.addAttribute("editAddress", new ShippingAddressDTO());
        modelMap.addAttribute("cartItemCount", cartService.getItemCart().size());
    }

    @GetMapping("")
    public String myAccount(ModelMap modelMap) {
        return "myAccount";
    }

    @GetMapping("/{section}")
    public String showSection(@PathVariable String section, ModelMap modelMap) {
        if ("orders".equals(section) || "personal".equals(section) || "wishlist".equals(section)) {
            modelMap.addAttribute("address", new ShippingAddressDTO());
        }
        return "myAccount";
    }

    @PostMapping("/personal/addAddress")
    public String addAdress(ModelMap modelMap,
                            @ModelAttribute("address") ShippingAddressDTO shippingAddressDTO,
                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/my-account/personal";
        }else {
            accountService.addShippingAddress(shippingAddressDTO);
            return "redirect:/my-account/personal";
        }
    }

    @PostMapping("/personal/editAddress/{id}")
    public String editAddress(ModelMap modelMap,
                              @ModelAttribute("editAddress") ShippingAddressDTO shippingAddressDTO,
                              BindingResult bindingResult,
                              @PathVariable Long id) {
        if(bindingResult.hasErrors()) {
            return "redirect:/my-account/personal";
        }else {
            accountService.editShippingAddress(shippingAddressDTO, id);
            return "redirect:/my-account/personal";
        }
    }

    @PostMapping("/personal/delAddress/{id}")
    public String delAddress(ModelMap modelMap, @PathVariable Long id) {
        accountService.delShippingAddress(id);
        return "redirect:/my-account/personal";
    }
}
