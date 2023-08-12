package com.LeBao.sales.services;

import com.LeBao.sales.models.Role;
import com.LeBao.sales.models.ShippingAddress;
import com.LeBao.sales.models.User;
import com.LeBao.sales.repositories.ShippingAddressRepository;
import com.LeBao.sales.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public String customerRegister(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "redirect:/login?same";
        }
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login?success";
    }

    public void adminRegister(User user) {
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String updateCustomerInfor(Long customerId, User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email này đã tồn tại";
        }
        User foundUser = userRepository.findById(customerId).get();
        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setEmail(user.getEmail());
        foundUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(foundUser);
        return "Update successfully";
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    public void addShippingAddress(ShippingAddress shippingAddress) {
        //String email = getCurrentUsername();
        //User user = userRepository.findByEmail(email).get();

        User user = userRepository.findById(1L).get();
        shippingAddress.setUser(user);
        shippingAddressRepository.save(shippingAddress);
        user.getShippingAddresses().add(shippingAddress);
        userRepository.save(user);
    }

    public void updateShippingAddress(Long shippingAddressId, ShippingAddress shippingAddress) {
        ShippingAddress foundShippingAddress = shippingAddressRepository.findById(shippingAddressId).get();
        userRepository.findById(1L).get().getShippingAddresses().remove(foundShippingAddress);

        foundShippingAddress.setAddress(shippingAddress.getAddress());
        foundShippingAddress.setCity(shippingAddress.getCity());
        foundShippingAddress.setCountry(shippingAddress.getCountry());
        shippingAddressRepository.save(foundShippingAddress);

        userRepository.findById(1L).get().getShippingAddresses().add(foundShippingAddress);
    }


    public void deleteShippingAddress(Long shippingAddressId) {
        //String email = getCurrentUsername();
        //User user = userRepository.findByEmail(email).get();

        User user = userRepository.findById(1L).get();
        user.getShippingAddresses().remove(shippingAddressRepository.findById(shippingAddressId).get());
        shippingAddressRepository.delete(shippingAddressRepository.findById(shippingAddressId).get());
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
