package com.LeBao.sales.controllers;
import com.LeBao.sales.entities.Product;
import com.LeBao.sales.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    private ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> search(@RequestParam("key") String keyword,
                                                @RequestParam(defaultValue = "0", required = false) int page,
                                                @RequestParam(value = "sort", required = false) String sortValue) {
        return ResponseEntity.ok().body(productService.search(keyword, page, sortValue));
    }
}
