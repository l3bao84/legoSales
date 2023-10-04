package com.LeBao.sales.services;

import com.LeBao.sales.entities.User;
import com.LeBao.sales.repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    private JwtService jwtService;

    public String authenticate(String username, String password, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            User user = userRepository.findByEmail(username).orElseThrow();
            String jwtToken = jwtService.generateToken(user);

            if (jwtToken != null) {
                response.setHeader("Authorization", "Bearer " + jwtToken);
                return "home";
            } else {
                return "redirect:/login?error";
            }
        } catch (AuthenticationException e) {
            return "redirect:/login?error";
        }

    }

}
