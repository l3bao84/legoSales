package com.LeBao.sales.controllers;

import com.LeBao.sales.DTO.OrderDTO;
import com.LeBao.sales.repositories.CategoryRepository;
import com.LeBao.sales.repositories.ProductRepository;
import com.LeBao.sales.services.CartService;
import com.LeBao.sales.services.CheckoutService;
import com.LeBao.sales.services.HomeService;
import com.LeBao.sales.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

//@RestController
@Controller
@CrossOrigin
public class HomeController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private HomeService homeService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CheckoutService checkoutService;

    @ModelAttribute
    public void prepareDataForHome(Model model) {
        model.addAttribute("cartItemCount", cartService.getItemCart().size());
        model.addAttribute("allThemes", categoryRepository.findAll());
        model.addAttribute("products", homeService.getRecommendedProducts());
        model.addAttribute("topPickProducts", homeService.getTopPickProducts());
        model.addAttribute("randomCat", homeService.getRandomCategory());
    }

    @GetMapping("/home")
    public String home(
                       @RequestParam(value = "orderId", required = false) String orderId,
                       @RequestParam(value = "pay", required = false) String paymentStatus) {
//        if(authentication != null && authentication.isAuthenticated()) {
//            if(paymentStatus != null && orderId != null) {
//                if(paymentStatus.equalsIgnoreCase("success")) {
//                    checkoutService.paymentSuccess(orderId,"PayPal(Payment successfull)");
//                }
//            }
//            return "home";
//        }
        if(paymentStatus != null && orderId != null) {
            if(paymentStatus.equalsIgnoreCase("success")) {
                checkoutService.paymentSuccess(orderId,"PayPal(Payment successfull)");
            }
        }
        return "home";
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> getImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.getImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
