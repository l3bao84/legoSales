package com.LeBao.sales.controllers;

import com.LeBao.sales.DTO.CartItemDTO;
import com.LeBao.sales.models.Cart;
import com.LeBao.sales.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/getCart/{userId}")
    public ResponseEntity<List<Cart>> getCart(@PathVariable Long userId) {
        if(cartService.getCart(userId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cartService.getCart(userId));
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(cartService.getCart(userId));
    }

    @PostMapping("/addItemToCart/{userId}")
    public ResponseEntity<String> addItemToCart(@PathVariable Long userId, @RequestBody CartItemDTO cartItemDTO) {
        String message = cartService.addItemToCart(userId,cartItemDTO);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/deleteItemInCart/{cartItemId}")
    public ResponseEntity<String> deleteItemInCart(@PathVariable Long cartItemId) {
        cartService.deleteItemInCart(cartItemId);
        return ResponseEntity.ok("Delete successfully");
    }
}
