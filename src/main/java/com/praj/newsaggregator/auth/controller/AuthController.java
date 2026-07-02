package com.praj.newsaggregator.auth.controller;

import com.praj.newsaggregator.auth.dto.AuthResponse;
import com.praj.newsaggregator.auth.dto.LoginRequest;
import com.praj.newsaggregator.auth.dto.RegisterRequest;
import com.praj.newsaggregator.auth.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthResponse register(
            @RequestBody @Valid RegisterRequest request) {

        return authenticationService.register(request);

    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody @Valid LoginRequest request) {

        return authenticationService.login(request);

    }

}