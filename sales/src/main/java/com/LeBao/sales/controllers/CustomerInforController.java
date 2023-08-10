package com.LeBao.sales.controllers;

import com.LeBao.sales.models.ShippingAddress;
import com.LeBao.sales.models.User;
import com.LeBao.sales.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customerInfor")
@RequiredArgsConstructor
public class CustomerInforController {

    @Autowired
    private UserService userService;

    @PostMapping("/updateCustomerInfor/{customerId}")
    public ResponseEntity<String> updateCustomerInfor(@PathVariable Long customerId, @RequestBody User user) {
        String response = userService.updateCustomerInfor(customerId, user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.getUserById(userId));
    }

    @PostMapping("/addShippingAddress")
    public ResponseEntity<String> addShippingAddress(@RequestBody ShippingAddress shippingAddress) {
        userService.addShippingAddress(shippingAddress);
        return ResponseEntity.ok("Add shipping address successfully");
    }

    @PostMapping("/updateShippingAddress/{shippingAddressId}")
    public ResponseEntity<String> updateShippingAddress(@PathVariable Long shippingAddressId, @RequestBody ShippingAddress shippingAddress) {
        userService.updateShippingAddress(shippingAddressId, shippingAddress);
        return ResponseEntity.ok("Update shipping address successfully");
    }

    @PostMapping("/deleteShippingAddress/{shippingAddressId}")
    public ResponseEntity<String> deleteShippingAddress(@PathVariable Long shippingAddressId) {
        userService.deleteShippingAddress(shippingAddressId);
        return ResponseEntity.ok("Delete shipping address successfully");
    }
}
