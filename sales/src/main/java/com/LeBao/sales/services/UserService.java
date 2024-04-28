package com.LeBao.sales.services;

import com.LeBao.sales.entities.Role;
import com.LeBao.sales.entities.User;
import com.LeBao.sales.repositories.ShippingAddressRepository;
import com.LeBao.sales.repositories.UserRepository;
import com.LeBao.sales.requests.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal)
                .map(principal -> principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString())
                .orElse(null);
    }

    public User customerRegister(RegistrationRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            return null;
        }else {
            User user = new User();

            user.setFirstName(request.getFirstname());
            user.setLastName(request.getLastname());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Role.USER);

            return userRepository.save(user);
        }
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
