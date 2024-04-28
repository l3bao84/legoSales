package com.LeBao.sales.services;

import com.LeBao.sales.DTO.ReviewDTO;
import com.LeBao.sales.entities.Product;
import com.LeBao.sales.entities.Review;
import com.LeBao.sales.entities.User;
import com.LeBao.sales.repositories.CategoryRepository;
import com.LeBao.sales.repositories.ProductRepository;
import com.LeBao.sales.repositories.ReviewRepository;
import com.LeBao.sales.repositories.UserRepository;
import com.LeBao.sales.responses.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public ProductResponse getProduct(Long productId) {
        if(productRepository.findById(productId).isPresent()) {
            Product product = productRepository.findById(productId).get();

            ProductResponse response = ProductResponse.builder()
                    .id(product.getProductId())
                    .productName(product.getProductName())
                    .productDescription(product.getProductDescription())
                    .price(product.getPrice())
                    .stock(product.getStock())
                    .pieces(product.getPieces())
                    .images(product.getImages())
                    .categoryId(product.getCategory().getCategoryId())
                    .cartItems(product.getCartItems())
                    .wishlistProducts(product.getWishlistProducts())
                    .orderDetails(product.getOrderDetails())
                    .reviews(product.getReviews()).build();

            return response;
        }
        return null;
    }

    public List<Product> getAllByThemeId(Long categoryId) {
        if(categoryRepository.findById(categoryId).isPresent()) {
            return productRepository.findAllByCategoryId(categoryId);
        }
        return null;
    }

    public List<Product> getRecommendedProducts() {
        List<Product> products = productRepository.findAll();
        Collections.reverse(products);
        List<Product> recommendedProducts = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            recommendedProducts.add(products.get(i));
        }
        return recommendedProducts;
    }

    public List<Product> getTopPickProducts() {
        List<Product> products = productRepository.findAll();
        List<Product> toppickProducts = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i <= 3; i++) {
            int randomPosition = random.nextInt(products.size());
            toppickProducts.add(products.get(randomPosition));
            products.remove(randomPosition);
        }
        return toppickProducts;
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

    public Product imageReviewsFileName(Product product) {
        Set<Review> reviews = product.getReviews();
        for (Review review:reviews) {
            List<String> imageReviewsFilePath = review.getImageReviews();
            List<String> imageReviewsFileName = new ArrayList<>();
            for (String filePath:imageReviewsFilePath) {
                Path path = FileSystems.getDefault().getPath(filePath);
                imageReviewsFileName.add(path.getFileName().toString());
            }
            review.setImageReviews(imageReviewsFileName);
        }
        product.setReviews(reviews);
        return product;
    }

    public String decialFormat(Double avgRating) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return decimalFormat.format(avgRating);
    }

    public Map<Integer,Long> ratingCount(Long productId) {
        Map<Integer,Long> ratingCountMap = new HashMap<>();
        for(int i = 5; i >= 1; i--) {
            int currentRV = i;
            long count = productRepository.findById(productId).get().getReviews()
                    .stream()
                    .filter(review -> review.getRating() == currentRV)
                    .count();
            ratingCountMap.put(currentRV, count);
        }
        return ratingCountMap;
    }

    public void addReview(Long productId, ReviewDTO reviewDTO, MultipartFile[] files) throws IOException {
        Optional<Product> oProduct = productRepository.findById(productId);
        Optional<User> oUser = userRepository.findById(31L);
        Review review = new Review();

        if(oProduct.isPresent() && oUser.isPresent()) {
            Product product = oProduct.get();
            User user = oUser.get();

            review.setProduct(product);
            review.setUser(user);
            review.setReviewDate(LocalDate.now());
            review.setImageReviews(storageService.upload(files));
            review.setContent(reviewDTO.getContent());
            review.setRating(reviewDTO.getRating());

            reviewRepository.save(review);

            product.getReviews().add(review);
            productRepository.save(product);

            user.getReviews().add(review);
            userRepository.save(user);
        }
    }

    public Page<Product> search(String keyword, int page, String sortValue) {
        int pageSize = 8;
        Pageable pageable; Page<Product> products = null;
        if(sortValue != null) {
            if(sortValue.equalsIgnoreCase("Price: Low to High")) {
                pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.asc("price")));
                products = productRepository.findByProductNameContainingIgnoreCase(keyword, pageable);
            }else if(sortValue.equalsIgnoreCase("Price: High to Low")) {
                pageable = PageRequest.of(page,pageSize,Sort.by(Sort.Order.desc("price")));
                products = productRepository.findByProductNameContainingIgnoreCase(keyword, pageable);
            }else if(sortValue.equalsIgnoreCase("A-Z")) {
                pageable = PageRequest.of(page,pageSize,Sort.by(Sort.Order.asc("productName")));
                products = productRepository.findByProductNameContainingIgnoreCase(keyword, pageable);
            }else {
                pageable = PageRequest.of(page, pageSize);
                products = productRepository.findByProductNameContainingIgnoreCase(keyword, pageable);
            }
        }

        return products;
    }
}