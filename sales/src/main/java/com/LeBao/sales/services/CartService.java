package com.LeBao.sales.services;

import com.LeBao.sales.entities.*;
import com.LeBao.sales.repositories.CartItemRepository;
import com.LeBao.sales.repositories.CartRepository;
import com.LeBao.sales.repositories.ProductRepository;
import com.LeBao.sales.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
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

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;

        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
        }

        return username;
    }

    public List<CartItem> getItemCart() {
        String username = getCurrentUsername();
        List<CartItem> cartItems = new ArrayList<>();
        if(username == null) {
            return cartItems;
        }else {
            Optional<User> user = userRepository.findByEmail(username);
            if(user.isPresent()) {
                Cart cart = user.get().getCart();
                if(cart != null) {
                    cartItems = cart.getCartItems().stream().toList();
                }
            }
        }
        return cartItems;
    }

    public void addItemToCart(Long id, int quantity) {
        String username = getCurrentUsername();
        Optional<User> optionalUser = userRepository.findByEmail(username);
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalUser.isPresent() && optionalProduct.isPresent()) {
            User user = optionalUser.get();
            Product product = optionalProduct.get();

            // Kiểm tra xem người dùng đã có giỏ hàng chưa
            Cart cart = user.getCart();
            if (cart == null) {
                cart = new Cart();
                cart.setCreationDate(LocalDate.now());
                cart.setUser(user);
                cart.setCartItems(new HashSet<>());
                user.setCart(cart);
                cartRepository.save(cart);
            }

            // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
            boolean existingCartItemFound = false;
            for (CartItem cartItem : cart.getCartItems()) {
                if (cartItem.getProduct().equals(product)) {
                    if(cartItem.getQuantity() == 3) {
                        existingCartItemFound = true;
                        break;
                    }else if(cartItem.getQuantity() >= 1 && cartItem.getQuantity() < 3){
                        cartItem.setQuantity(cartItem.getQuantity() + 1);
                        existingCartItemFound = true;
                        break;
                    }
                }
            }

            if (!existingCartItemFound) {
                CartItem cartItem = new CartItem();
                cartItem.setQuantity(quantity);
                cartItem.setPrice(product.getPrice());
                cartItem.setProduct(product);
                cartItem.setCart(cart);


                cart.getCartItems().add(cartItem);
            }

            userRepository.save(user);
        }
    }

    public void delCartItem(Long id) {
        String username = getCurrentUsername();
        Optional<User> optionalUser = userRepository.findByEmail(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Cart cart = user.getCart();

            // Tìm và xóa CartItem theo id
            Iterator<CartItem> iterator = cart.getCartItems().iterator();
            while (iterator.hasNext()) {
                CartItem cartItem = iterator.next();
                if (cartItem.getCartItemId() == id) {
                    iterator.remove();
                    cartItemRepository.deleteById(id);
                }
            }

            // Kiểm tra nếu không còn CartItem nào, thì xóa luôn Cart
            if (cart.getCartItems().isEmpty()) {
                cartRepository.deleteById(cart.getCartId());
                user.setCart(null); // Loại bỏ liên kết với Cart
            }
            userRepository.save(user);
        }
    }

    public void dellAllInCart(Long id) {
        String username = getCurrentUsername();
        Optional<User> optionalUser = userRepository.findByEmail(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Cart cart = user.getCart();

            // Tìm và xóa CartItem theo id
            Iterator<CartItem> iterator = cart.getCartItems().iterator();
            while (iterator.hasNext()) {
                CartItem cartItem = iterator.next();
                if (cartItem.getCartItemId() == id) {
                    iterator.remove();
                    cartItemRepository.deleteById(id);
                }
            }

            cartRepository.deleteById(cart.getCartId());
            user.setCart(null);
            userRepository.save(user);
        }
    }

    public void reduceQuantity(long id, int quantity) {
        String username = getCurrentUsername();
        Optional<User> optionalUser = userRepository.findByEmail(username);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            Cart cart = user.getCart();
            List<CartItem> cartItems = cart.getCartItems().stream().toList();
            for (CartItem item : cartItems){
                if(item.getCartItemId() == id) {
                    item.setQuantity(quantity);
                    break;
                }
            }
            userRepository.save(user);
        }
    }
}
