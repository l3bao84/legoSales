package com.LeBao.sales.controllers;

import com.LeBao.sales.entities.User;
import com.LeBao.sales.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @ModelAttribute
    public void prepareDataForLogin(ModelMap modelMap) {
        modelMap.addAttribute("register", new User());
//        modelMap.addAttribute("authenticate", new AuthenticateRequest());
    }

    @GetMapping("/login")
    public String login(ModelMap modelMap) {
        return "login";
    }

}
