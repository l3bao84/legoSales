package com.LeBao.sales.controllers;

import com.LeBao.sales.entities.User;
import com.LeBao.sales.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String signup(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/registration")
    public String customerRegister(@ModelAttribute("user") User user,
                                                   BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/signup?hasError";
        }
        return userService.customerRegister(user);
    }

//    @PostMapping("/adminRegister")
//    public ResponseEntity<String> adminRegister(@RequestBody User user) {
//        userService.adminRegister(user);
//        return ResponseEntity.ok("Successfully");
//    }
}
