package com.LeBao.sales.controllers;

import com.LeBao.sales.DTO.ReviewDTO;
import com.LeBao.sales.DTO.ShippingAddressDTO;
import com.LeBao.sales.entities.Product;
import com.LeBao.sales.repositories.CategoryRepository;
import com.LeBao.sales.repositories.ProductRepository;
import com.LeBao.sales.services.CartService;
import com.LeBao.sales.services.HomeService;
import com.LeBao.sales.services.ProductService;
import com.LeBao.sales.services.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private HomeService homeService;

    @Autowired
    private CartService cartService;

    @ModelAttribute
    public void prepareDataForProduct(ModelMap modelMap) {
        modelMap.addAttribute("allThemes", categoryRepository.findAll());
        modelMap.addAttribute("products", homeService.getRecommendedProducts());
        modelMap.addAttribute("cartItemCount", cartService.getItemCart().size());
        modelMap.addAttribute("review", new ReviewDTO());
    }

    @GetMapping("/getProduct/{productId}")
    public String getProduct(ModelMap modelMap, @PathVariable Long productId) {
        modelMap.addAttribute("product", productService.imageReviewsFileName(productRepository.findById(productId).get()));
        modelMap.addAttribute("themeId", productRepository.findById(productId).get().getCategory().getCategoryId());
        modelMap.addAttribute("themeName", productRepository.findById(productId).get().getCategory().getCategoryName());
        modelMap.addAttribute("imageList", productService.imagesFileName(productId));
        modelMap.addAttribute("avgRating", productService.decialFormat(
                productRepository.findById(productId).get().getReviews()
                        .stream()
                        .mapToDouble(review -> review.getRating())
                        .average()
                        .orElse(0.0)));
        modelMap.addAttribute("ratingCount", productService.ratingCount(productId));
        return "detailProduct";
    }

    @PostMapping("/addReview/{productId}")
    public String addReview(ModelMap modelMap,
                            @PathVariable Long productId,
                            @ModelAttribute("review") ReviewDTO reviewDTO,
                            BindingResult bindingResult,
                            @RequestParam("files") MultipartFile[] files) throws IOException {
        if(bindingResult.hasErrors()) {
            modelMap.addAttribute("product", productRepository.findById(productId).get());
            modelMap.addAttribute("themeId", productRepository.findById(productId).get().getCategory().getCategoryId());
            modelMap.addAttribute("themeName", productRepository.findById(productId).get().getCategory().getCategoryName());
            modelMap.addAttribute("imageList", productService.imagesFileName(productId));
            modelMap.addAttribute("avgRating", productService.decialFormat(
                    productRepository.findById(productId).get().getReviews()
                            .stream()
                            .mapToDouble(review -> review.getRating())
                            .average()
                            .orElse(0.0)));
            modelMap.addAttribute("ratingCount", productService.ratingCount(productId));
            return "redirect:/product/getProduct/" + productId;
        }else {
            productService.addReview(productId,reviewDTO,files);
        }
        modelMap.addAttribute("product", productRepository.findById(productId).get());
        modelMap.addAttribute("themeId", productRepository.findById(productId).get().getCategory().getCategoryId());
        modelMap.addAttribute("themeName", productRepository.findById(productId).get().getCategory().getCategoryName());
        modelMap.addAttribute("imageList", productService.imagesFileName(productId));
        modelMap.addAttribute("avgRating", productService.decialFormat(
                productRepository.findById(productId).get().getReviews()
                .stream()
                .mapToDouble(review -> review.getRating())
                .average()
                .orElse(0.0)));
        modelMap.addAttribute("ratingCount", productService.ratingCount(productId));
        return "redirect:/product/getProduct/" + productId;
    }

    @GetMapping("/getAllByThemeId/{categoryId}")
    public String getAllByThemeId(@PathVariable Long categoryId, ModelMap modelMap) {
        List<Product> products = productService.getAllByThemeId(categoryId);
        return "";
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
