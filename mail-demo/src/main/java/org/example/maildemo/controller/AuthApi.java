package org.example.maildemo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.maildemo.dto.BearerToken;
import org.example.maildemo.dto.LoginRequest;
import org.example.maildemo.dto.RegisterRequest;
import org.example.maildemo.model.ResponseMessage;
import org.example.maildemo.security.UsernamePasswordAuthenticationProvider;
import org.example.maildemo.service.AuthService;
import org.example.maildemo.utils.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final UsernamePasswordAuthenticationProvider providerManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<BearerToken> login(@RequestBody @Valid LoginRequest loginRequest) {
        Authentication authentication = providerManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtTokenUtil.generateToken(authentication.getPrincipal().toString(),null);

        BearerToken bearerToken = getBearerToken(accessToken);

        return ResponseEntity.ok(bearerToken);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseMessage> register(@RequestBody RegisterRequest registerInformations) {
        return authService.register(registerInformations);
    }

    private static BearerToken getBearerToken(String accessToken) {
        return BearerToken.builder().accessToken(accessToken).tokenType("Bearer ").build();
    }
}
