package com.LeBao.sales.controllers;

import com.LeBao.sales.models.Product;
import com.LeBao.sales.services.ProductService;
import com.LeBao.sales.services.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StorageService storageService;

    @GetMapping("/getProduct/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        if(productService.getProduct(productId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProduct(productId));
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        String status = productService.addProduct(product);
        return ResponseEntity.ok(status);
    }


    @PostMapping("/addImage")
    public ResponseEntity<String> addImage(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = storageService.uploadFileToFileSystem(file);
        return ResponseEntity.ok(filePath);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable Long productId) throws IOException {
        byte[] imageData = storageService.downloadImageFromFileSystem(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @PostMapping("/updateProduct/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        productService.updateProduct(productId, product);
        return ResponseEntity.ok("Update successfully");
    }

    @PostMapping("/deleteProduct/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Delete successfully");
    }
}
