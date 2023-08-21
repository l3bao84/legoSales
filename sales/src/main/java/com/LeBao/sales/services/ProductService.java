package com.LeBao.sales.services;

import com.LeBao.sales.models.Category;
import com.LeBao.sales.models.Product;
import com.LeBao.sales.repositories.CategoryRepository;
import com.LeBao.sales.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product getProduct(Long productId) {
        if(productRepository.findById(productId).isPresent()) return productRepository.findById(productId).get();
        return null;
    }

    public List<Product> getAllByThemeId(Long categoryId) {
        if(categoryRepository.findById(categoryId).isPresent()) {
            return productRepository.findAllByCategoryId(categoryId);
        }
        return null;
    }

//    public String addProduct(Product product) {
//        List<Image>
//        product.setImage("C:\\Users\\HELLO\\OneDrive\\Documents\\Projects\\sales\\sales\\src\\main\\resources\\images\\228a6b12-71df-4ab3-8951-5a7eab8ab06c.png");
//        if(categoryRepository.findById(1L).isPresent()) {
//            product.setCategory(categoryRepository.findById(1L).get());
//            productRepository.save(product);
//            categoryRepository.findById(1L).get().getProducts().add(product);
//            categoryRepository.save(categoryRepository.findById(1L).get());
//            return "Add successfully";
//        }
//        return "Add failed";
//    }

    public void updateProduct(Long productId, Product product) {
        if(productRepository.findById(productId).isPresent()) {
            Product foundProduct = productRepository.findById(productId).get();
            foundProduct.setProductName(product.getProductName());
            foundProduct.setProductDescription(product.getProductDescription());
            foundProduct.setPrice(product.getPrice());
            foundProduct.setStock(product.getStock());

            // Nếu sửa category thì sẽ xóa sản phẩm ở category cũ đi
            // So sánh nếu categoryId cũ bằng mới thì sẽ ko đổi, ngược lại sẽ cập nhật
            // foundProduct.getCategory().getCategoryId() != product.getCategory().getCategoryId()
            if(foundProduct.getCategory().getCategoryId() == 1L) {

                // Xóa sản phảm đó khỏi danh sách trước khi set thuộc tính mới và lưu
                categoryRepository.findById(foundProduct.getCategory().getCategoryId()).get().getProducts().remove(foundProduct);

                // Set cho sản phẩm đó category mới và lưu
                foundProduct.setCategory(categoryRepository.findById(2L).get());
                productRepository.save(foundProduct);

                categoryRepository.findById(2L).get().getProducts().add(foundProduct);
            }else {
                productRepository.save(foundProduct);
            }
        }
    }


    // Khi xóa bất kỳ sản phẩm nào thì sẽ danh sách sản phẩm thuộc danh mục đó sẽ xóa đi sản phẩm tương ứng
    public void deleteProduct(Long productId) {
        Product foundProduct = productRepository.findById(productId).get();
        Long categoryId = foundProduct.getCategory().getCategoryId();

        categoryRepository.findById(categoryId).get().getProducts().remove(foundProduct);
        categoryRepository.save(categoryRepository.findById(categoryId).get());

        productRepository.deleteById(productId);
    }

    public List<String> imagesFileName(Long productId) {
        List<String> imagesFileName = new ArrayList<>();
        for (String filePath:productRepository.findById(productId).get().getImages()) {
            imagesFileName.add(Paths.get(filePath).getFileName().toString());
        }
        return imagesFileName;
    }
}