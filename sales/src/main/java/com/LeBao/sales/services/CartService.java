package com.LeBao.sales.services;

import com.LeBao.sales.DTO.CartItemDTO;
import com.LeBao.sales.models.Cart;
import com.LeBao.sales.models.CartItem;
import com.LeBao.sales.models.Product;
import com.LeBao.sales.models.User;
import com.LeBao.sales.repositories.CartItemRepository;
import com.LeBao.sales.repositories.CartRepository;
import com.LeBao.sales.repositories.ProductRepository;
import com.LeBao.sales.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Cart> getCart(Long userId) {
//        String email = userService.getCurrentUsername();
//        User user = userRepository.findByEmail(email).get();

        List<Cart> carts = cartRepository.findAll();
        List<Cart> foundCarts = new ArrayList<Cart>();
        for (Cart cart : carts){
            if(cart.getUser().getUserId() == userId) {
                foundCarts.add(cart);
            }
        }
        if(foundCarts.isEmpty()) {
            return null;
        }
        return foundCarts;
    }

    public Cart findCartByUserId(Long userId) {
        for (Cart cart:cartRepository.findAll().stream().toList()) {
            if(cart.getUser().getUserId() == userId) {
                return cart;
            }
        }
        return null;
    }

    public String addItemToCart(Long userId, CartItemDTO cartItemDTO) {
        if(findCartByUserId(userId) == null) {
            Cart cart = Cart.builder()
                    .creationDate(LocalDate.now())
                    .user(userRepository.findById(userId).get())
                    .build();
            cartRepository.save(cart);

            CartItem cartItem = CartItem.builder()
                    .cart(cart)
                    .product(productRepository.findById(cartItemDTO.getProductId()).get())
                    .quantity(cartItemDTO.getQuantity())
                    .price(cartItemDTO.getPrice())
                    .build();
            cartItemRepository.save(cartItem);
            cartRepository.findById(cart.getCartId()).get().getCartItems().add(cartItem);
            cartRepository.save(cartRepository.findById(cart.getCartId()).get());
            productRepository.findById(cartItemDTO.getProductId()).get().getCartItems().add(cartItem);
            productRepository.save(productRepository.findById(cartItemDTO.getProductId()).get());
            return "Add an item to cart successfully";
        }
        Cart cart = findCartByUserId(userId);
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(productRepository.findById(cartItemDTO.getProductId()).get())
                .quantity(cartItemDTO.getQuantity())
                .price(cartItemDTO.getPrice())
                .build();
        cartItemRepository.save(cartItem);
        cartRepository.findById(cart.getCartId()).get().getCartItems().add(cartItem);
        cartRepository.save(cartRepository.findById(cart.getCartId()).get());
        productRepository.findById(cartItemDTO.getProductId()).get().getCartItems().add(cartItem);
        productRepository.save(productRepository.findById(cartItemDTO.getProductId()).get());
        return "Add an item to cart successfully";
    }

    public void deleteItemInCart(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).get();
        productRepository.findById(cartItem.getProduct().getProductId()).get()
                .getCartItems().remove(cartItem);
        cartRepository.findById(cartItem.getCart().getCartId()).get()
                .getCartItems().remove(cartItem);

        productRepository.save(productRepository.findById(cartItem.getProduct().getProductId()).get());
        cartRepository.save(cartRepository.findById(cartItem.getCart().getCartId()).get());
        cartItemRepository.delete(cartItem);
    }
}
