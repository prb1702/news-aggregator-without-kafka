package com.praj.newsaggregator.auth.service;

import com.praj.newsaggregator.auth.dto.AuthResponse;
import com.praj.newsaggregator.auth.dto.LoginRequest;
import com.praj.newsaggregator.auth.dto.RegisterRequest;
import com.praj.newsaggregator.auth.entity.User;
import com.praj.newsaggregator.auth.repository.UserRepository;
import com.praj.newsaggregator.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }

        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        String token = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        java.util.Collections.emptyList()
                )
        );

        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getEmail(),

                        request.getPassword()

                )

        );

        User user = userRepository.findByEmail(request.getEmail())

                .orElseThrow();

        String token = jwtService.generateToken(

                new org.springframework.security.core.userdetails.User(

                        user.getEmail(),

                        user.getPassword(),

                        java.util.Collections.emptyList()

                )

        );

        return AuthResponse.builder()

                .token(token)

                .build();

    }

}