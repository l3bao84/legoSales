package com.LeBao.sales.services;

import com.LeBao.sales.entities.CartItem;
import com.LeBao.sales.entities.ShippingAddress;
import com.LeBao.sales.entities.User;
import com.LeBao.sales.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CheckoutService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

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

    public List<ShippingAddress> getShippingAddress() {
        String username = getCurrentUsername();
        if(username != null) {
            Optional<User> optionalUser = userRepository.findByEmail(username);
            if(optionalUser.isPresent()) {
                User user = optionalUser.get();
                return user.getShippingAddresses().stream().toList();
            }
        }
        return null;
    }

    public List<CartItem> getCartItem() {
        return cartService.getItemCart();
    }

    public void checkoutHandler(String data) {
        String username = getCurrentUsername();
        User user = null;
        if(username != null) {
            Optional<User> optionalUser = userRepository.findByEmail(username);
            if(optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        }

        String[] pairs = data.split(",");
        Map<Long,Integer> idAndQuantity = new HashMap<>();
        for (String pair:pairs) {
            String[] elements = pair.split(":");
            idAndQuantity.put(Long.parseLong(elements[0]), Integer.parseInt(elements[1]));
        }
        for (Long key : idAndQuantity.keySet()) {
            Integer value = idAndQuantity.get(key);
            List<CartItem> cartItems = getCartItem();
            for (CartItem item:cartItems) {
                if(item.getCartItemId() == key) {
                    item.setQuantity(value);
                }
            }
        }
        userRepository.save(user);
    }
}
