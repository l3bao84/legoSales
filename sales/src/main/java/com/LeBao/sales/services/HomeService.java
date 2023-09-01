package com.LeBao.sales.services;

import com.LeBao.sales.models.Product;
import com.LeBao.sales.models.Review;
import com.LeBao.sales.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> searchProducts(String keyword, String sortValue, int page) {
//        return productRepository.findByProductNameContainingIgnoreCase(keyword, pageable);
        int pageSize = 8;
        Pageable pageable;
        if(sortValue.equalsIgnoreCase("Price: Low to High")) {
            pageable = PageRequest.of(page,pageSize,Sort.by(Sort.Order.asc("price")));
            return productRepository.findAll(pageable);
        }else if(sortValue.equalsIgnoreCase("Price: High to Low")) {
            pageable = PageRequest.of(page,pageSize,Sort.by(Sort.Order.desc("price")));
            return productRepository.findAll(pageable);
        }else if(sortValue.equalsIgnoreCase("A-Z")) {
            pageable = PageRequest.of(page,pageSize,Sort.by(Sort.Order.asc("productName")));
            return productRepository.findAll(pageable);
        }
        pageable = PageRequest.of(page, pageSize);
        return productRepository.findAll(pageable);
    }
}
