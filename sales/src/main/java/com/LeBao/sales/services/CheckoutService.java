package com.LeBao.sales.services;

import com.LeBao.sales.DTO.OrderDTO;
import com.LeBao.sales.entities.*;
import com.LeBao.sales.repositories.OrderDetailRepository;
import com.LeBao.sales.repositories.OrderRepository;
import com.LeBao.sales.repositories.UserRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CheckoutService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmailService emailService;


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

    public void cancelOrder(Long orderId) {
        String username = getCurrentUsername();
        if (username != null) {
            Optional<User> optionalUser = userRepository.findByEmail(username);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                orderDetailRepository.deleteByOrderId(orderId);
                orderRepository.deleteById(orderId);
            }
        }
    }



    public void order(OrderDTO orderDTO) {
        Order order = null;
        String username = getCurrentUsername();
        if (username != null) {
            Optional<User> optionalUser = userRepository.findByEmail(username);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                order = new Order();
                order.setUser(user);
                order.setOrderDate(LocalDate.now());
                order.setShippingAddress(orderDTO.getShippingAddress());
                order.setShippingMethod(orderDTO.getShippingMethod());
                order.setTotalAmount(orderDTO.getTotalAmount());
                order.setPaymentStatus(orderDTO.getPaymentStatus());
                order.setOrderStatus("Preparing the order");
                order.setOrderDetails(new HashSet<OrderDetail>());

                orderRepository.save(order);

                Cart cart = user.getCart();
                if(cart != null) {
                    for (CartItem item:cart.getCartItems()) {
                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setOrder(order);
                        orderDetail.setProduct(item.getProduct());
                        orderDetail.setQuantity(item.getQuantity());
                        orderDetail.setUnitPrice(item.getPrice());

                        cartService.delCartItem(item.getCartItemId());

                        orderDetailRepository.save(orderDetail);
                        order.getOrderDetails().add(orderDetail);
                    }
                }
                if(user.getOrders() == null) {
                    Set<Order> orders = new HashSet<>();
                    orders.add(order);
                    user.setOrders(orders);

                }else {
                    user.getOrders().add(order);
                }
                userRepository.save(user);
                emailService.sendEmail(order);
            }
        }
    }
}
