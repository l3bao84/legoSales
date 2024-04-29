package com.LeBao.sales.controllers;

import com.LeBao.sales.entities.Product;
import com.LeBao.sales.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/topPicks")
    public ResponseEntity<List<Product>> getTopPicks() {
        return ResponseEntity.ok().body(productService.getTopPickProducts());
    }

    @GetMapping("/recommendations")
    public ResponseEntity<List<Product>> getRecommendedProducts() {
        return ResponseEntity.ok().body(productService.getRecommendedProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable Long productId) {
        if(productService.getProduct(productId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No product has id: " + productId);
        }else {
            return ResponseEntity.ok().body(productService.getProduct(productId));
        }
    }

//    @PostMapping("/addProduct")
//    public ResponseEntity<String> addProduct(@RequestBody Product product) {
//        String status = productService.addProduct(product);
//        return ResponseEntity.ok(status);
//    }

//    @PostMapping("/addImage")
//    public ResponseEntity<List<String>> addImage(@RequestParam("files") MultipartFile[] files) throws IOException {
//        return ResponseEntity.ok(storageService.uploadFileToFileSystem(files));
//    }
//
//    @PostMapping("/addImageToProduct/{productId}")
//    public ResponseEntity<String> addImageToProduct(@PathVariable Long productId) {
//        return ResponseEntity.ok(productService.addImageToProduct(productId));
//    }

//    @GetMapping("/{productId}")
//    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable Long productId) throws IOException {
//        byte[] imageData = storageService.downloadImageFromFileSystem(productId);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png"))
//                .body(imageData);
//    }

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
