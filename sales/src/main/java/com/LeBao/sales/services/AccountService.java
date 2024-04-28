package com.LeBao.sales.services;

import com.LeBao.sales.DTO.PersonalInfoDTO;
import com.LeBao.sales.entities.ShippingAddress;
import com.LeBao.sales.entities.User;
import com.LeBao.sales.repositories.ShippingAddressRepository;
import com.LeBao.sales.repositories.UserRepository;
import com.LeBao.sales.requests.AddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;

    private final ShippingAddressRepository shippingAddressRepository;

    public User getCurrentUsername() {
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

        User user = null;
        if(userRepository.findByEmail(username).isPresent()) {
            user = userRepository.findByEmail(username).get();
        }
        return user;
    }

    public PersonalInfoDTO getPersonalInfo() {

        User user = getCurrentUsername();
        PersonalInfoDTO personalInfoDTO = null;
        if(user != null) {
            personalInfoDTO = userRepository.getPersonalInfo(user.getEmail());
            personalInfoDTO.setAddress(user.getShippingAddresses().stream().toList().get(0).getAddress());
            personalInfoDTO.setPhonenumber(user.getShippingAddresses().stream().toList().get(0).getPhoneNumber());
        }

        return personalInfoDTO;
    }

    public List<ShippingAddress> getShippingAddress() {
        return getCurrentUsername().getShippingAddresses().stream().toList();
    }

    public ShippingAddress add(AddressRequest request) {
        User user = getCurrentUsername();
        ShippingAddress address = new ShippingAddress();
        if(user != null) {
            address = ShippingAddress.builder()
                    .fullName(request.getFullName())
                    .phoneNumber(request.getPhoneNumber())
                    .address(request.getAddress())
                    .city(request.getCity())
                    .user(user)
                    .build();

            shippingAddressRepository.save(address);
            user.getShippingAddresses().add(address);
        }
        return address;
    }

    public ShippingAddress update(Long id, AddressRequest request) {
        ShippingAddress address = null;
        if(shippingAddressRepository.findById(id).isPresent()) {
            address = shippingAddressRepository.findById(id).get();

            if(request.getFullName() != null
                    && !request.getFullName().trim().isEmpty()
                    && !request.getFullName().trim().equalsIgnoreCase(address.getFullName())) {
                address.setFullName(request.getFullName().trim());
            }

            if(request.getPhoneNumber() != null
                    && !request.getPhoneNumber().trim().isEmpty()
                    && !request.getPhoneNumber().trim().equalsIgnoreCase(address.getPhoneNumber())) {
                address.setPhoneNumber(request.getPhoneNumber().trim());
            }

            if(request.getAddress() != null
                    && !request.getAddress().trim().isEmpty()
                    && !request.getAddress().trim().equalsIgnoreCase(address.getAddress())) {
                address.setAddress(request.getAddress().trim());
            }

            if(request.getCity() != null
                    && !request.getCity().trim().isEmpty()
                    && !request.getCity().trim().equalsIgnoreCase(address.getCity())) {
                address.setCity(request.getCity().trim());
            }
            shippingAddressRepository.save(address);
        }
        return address;
    }

    public void removeAddress(Long id) {
        User user = getCurrentUsername();
        if(shippingAddressRepository.findById(id).isPresent()) {
            ShippingAddress address = shippingAddressRepository.findById(id).get();
            shippingAddressRepository.deleteById(id);
            user.getShippingAddresses().remove(address);
            userRepository.save(user);
        }
    }
}
