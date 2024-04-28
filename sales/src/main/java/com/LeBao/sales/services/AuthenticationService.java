package com.LeBao.sales.services;

import com.LeBao.sales.entities.User;
import com.LeBao.sales.exceptions.AuthenticationFailedException;
import com.LeBao.sales.repositories.UserRepository;
import com.LeBao.sales.requests.AuthenticationRequest;
import com.LeBao.sales.responses.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public String authenticate(AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = (User) authentication.getPrincipal();

            return jwtService.generateToken(user);
        } catch (AuthenticationException e) {
            throw new AuthenticationFailedException("Authentication failed", e);
        }
    }

}
