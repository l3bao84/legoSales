package com.LeBao.sales.services;

import com.LeBao.sales.DTO.ShippingAddressDTO;
import com.LeBao.sales.entities.ShippingAddress;
import com.LeBao.sales.entities.User;
import com.LeBao.sales.repositories.ShippingAddressRepository;
import com.LeBao.sales.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

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
        String email = getCurrentUsername();
        User user = userRepository.findByEmail(email).get();
        return user.getShippingAddresses().stream().toList();
    }

    public void addShippingAddress(ShippingAddressDTO shippingAddressDTO) {
        String email = getCurrentUsername();
        User user = userRepository.findByEmail(email).get();
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setFullName(shippingAddressDTO.getFullName());
        shippingAddress.setPhoneNumber(shippingAddressDTO.getPhoneNumber());
        shippingAddress.setCity(shippingAddressDTO.getCity());
        shippingAddress.setAddress(shippingAddressDTO.getAddress());
        shippingAddress.setUser(user);
        shippingAddressRepository.save(shippingAddress);
        user.getShippingAddresses().add(shippingAddress);
        userRepository.save(user);
    }

    public void editShippingAddress(ShippingAddressDTO shippingAddressDTO, Long id) {
        String email = getCurrentUsername();
        User user = userRepository.findByEmail(email).get();
        ShippingAddress foundSA = shippingAddressRepository.findById(id).get();
        if(foundSA != null) {

            foundSA.setFullName(shippingAddressDTO.getFullName());
            foundSA.setPhoneNumber(shippingAddressDTO.getPhoneNumber());
            foundSA.setCity(shippingAddressDTO.getCity());
            foundSA.setAddress(shippingAddressDTO.getAddress());

            shippingAddressRepository.save(foundSA);
            userRepository.save(user);
        }
    }

    public void delShippingAddress(Long id) {
        String email = getCurrentUsername();
        User user = userRepository.findByEmail(email).get();
        for (ShippingAddress address: user.getShippingAddresses()) {
            if(address.getId() == id) {
                user.getShippingAddresses().remove(address);
            }
        }
        shippingAddressRepository.deleteById(id);
        userRepository.save(user);
    }
}
