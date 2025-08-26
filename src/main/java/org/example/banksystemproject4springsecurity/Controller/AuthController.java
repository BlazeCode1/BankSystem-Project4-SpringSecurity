package org.example.banksystemproject4springsecurity.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.banksystemproject4springsecurity.Api.ApiResponse;
import org.example.banksystemproject4springsecurity.Model.AuthResponse;
import org.example.banksystemproject4springsecurity.Model.LoginRequest;
import org.example.banksystemproject4springsecurity.Model.User;
import org.example.banksystemproject4springsecurity.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.status(200).body(new ApiResponse("Admin Added"));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
    return ResponseEntity.status(200).body(new AuthResponse(authService.login(loginRequest.getUsername(),loginRequest.getPassword())));
    }

    @GetMapping("/get/users")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.status(200).body(authService.getUsers());
    }
}
