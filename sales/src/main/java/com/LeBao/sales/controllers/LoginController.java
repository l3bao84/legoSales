package com.LeBao.sales.controllers;

import com.LeBao.sales.models.User;
import com.LeBao.sales.services.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {

//    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public String login(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "login";
    }

//    @PostMapping("/authenticate")
//    public String authenticate(@RequestParam("username") String username,
//                               @RequestParam("password") String password, HttpServletResponse response) {
//        return authenticationService.authenticate(username,password,response);
//    }
}