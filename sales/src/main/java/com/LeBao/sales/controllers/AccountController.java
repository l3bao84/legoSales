package com.LeBao.sales.controllers;

import com.LeBao.sales.DTO.ShippingAddressDTO;
import com.LeBao.sales.models.ShippingAddress;
import com.LeBao.sales.repositories.CategoryRepository;
import com.LeBao.sales.repositories.ProductRepository;
import com.LeBao.sales.repositories.UserRepository;
import com.LeBao.sales.services.AccountService;
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

    @GetMapping("")
    public String myAccount(ModelMap modelMap) {
        modelMap.addAttribute("allThemes", categoryRepository.findAll());
        modelMap.addAttribute("address", new ShippingAddressDTO());
        modelMap.addAttribute("addresses", accountService.getShippingAddress());
        modelMap.addAttribute("editAddress", new ShippingAddressDTO());
        return "myAccount";
    }

    @GetMapping("/{section}")
    public String showSection(@PathVariable String section, ModelMap modelMap) {
        if ("orders".equals(section)) {
            modelMap.addAttribute("address", new ShippingAddressDTO());
        } else if ("personal".equals(section)) {
            modelMap.addAttribute("address", new ShippingAddressDTO());
        } else if ("wishlist".equals(section)) {
            modelMap.addAttribute("address", new ShippingAddressDTO());
        }
        modelMap.addAttribute("editAddress", new ShippingAddressDTO());
        modelMap.addAttribute("allThemes", categoryRepository.findAll());
        modelMap.addAttribute("addresses", accountService.getShippingAddress());
        return "myAccount";
    }

    @PostMapping("/personal/addAddress")
    public String addAdress(ModelMap modelMap,
                            @ModelAttribute("address") ShippingAddressDTO shippingAddressDTO,
                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            modelMap.addAttribute("allThemes", categoryRepository.findAll());
            modelMap.addAttribute("address", new ShippingAddressDTO());
            modelMap.addAttribute("editAddress", new ShippingAddressDTO());
            modelMap.addAttribute("addresses", accountService.getShippingAddress());
            return "redirect:/my-account/personal";
        }else {
            accountService.addShippingAddress(shippingAddressDTO);
            modelMap.addAttribute("allThemes", categoryRepository.findAll());
            modelMap.addAttribute("address", new ShippingAddressDTO());
            modelMap.addAttribute("editAddress", new ShippingAddressDTO());
            modelMap.addAttribute("addresses", accountService.getShippingAddress());
            return "redirect:/my-account/personal";
        }
    }

    @PostMapping("/personal/editAddress/{id}")
    public String editAddress(ModelMap modelMap,
                              @ModelAttribute("editAddress") ShippingAddressDTO shippingAddressDTO,
                              BindingResult bindingResult,
                              @PathVariable Long id) {
        if(bindingResult.hasErrors()) {
            modelMap.addAttribute("editAddress", new ShippingAddressDTO());
            modelMap.addAttribute("allThemes", categoryRepository.findAll());
            modelMap.addAttribute("addresses", accountService.getShippingAddress());
            modelMap.addAttribute("address", new ShippingAddressDTO());
            return "redirect:/my-account/personal";
        }else {
            accountService.editShippingAddress(shippingAddressDTO, id);
            modelMap.addAttribute("editAddress", new ShippingAddressDTO());
            modelMap.addAttribute("allThemes", categoryRepository.findAll());
            modelMap.addAttribute("addresses", accountService.getShippingAddress());
            modelMap.addAttribute("address", new ShippingAddressDTO());
            return "redirect:/my-account/personal";
        }
    }

    @PostMapping("/personal/delAddress/{id}")
    public String delAddress(ModelMap modelMap, @PathVariable Long id) {
        accountService.delShippingAddress(id);
        modelMap.addAttribute("editAddress", new ShippingAddressDTO());
        modelMap.addAttribute("allThemes", categoryRepository.findAll());
        modelMap.addAttribute("addresses", accountService.getShippingAddress());
        modelMap.addAttribute("address", new ShippingAddressDTO());
        return "redirect:/my-account/personal";
    }
}
