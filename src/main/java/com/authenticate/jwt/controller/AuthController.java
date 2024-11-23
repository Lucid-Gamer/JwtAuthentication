package com.authenticate.jwt.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authenticate.jwt.entity.User;
import com.authenticate.jwt.payload.ApiResponse;
import com.authenticate.jwt.payload.LoginRequest;
import com.authenticate.jwt.payload.LoginResponse;
import com.authenticate.jwt.payload.RegisterUser;
import com.authenticate.jwt.payload.UserDTO;
import com.authenticate.jwt.security.util.JwtUtil;
import com.authenticate.jwt.service.UserService;

import lombok.AllArgsConstructor;


@RestController
@CrossOrigin
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/login")
    public <T> ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        User userDetails = (User) userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        LoginResponse resp = new LoginResponse(
            UserDTO.builder()
            .username(userDetails.getUsername())
            .firstName(userDetails.getFirstName())
            .lastName(userDetails.getLastName())
            .role(userDetails.getRoles().stream().map(r -> r.getRoleName().toString()).collect(Collectors.toSet()))
            .build(), token
            );
        return new ResponseEntity<ApiResponse<LoginResponse>>(new ApiResponse<LoginResponse>("Success", resp, true , "000"),HttpStatus.ACCEPTED);
    }
    

    @PostMapping("/register")
    public <T> ResponseEntity<?> postMethodName(@RequestBody RegisterUser registerUser) {
        try {
            UserDTO userDTO = userService.registerUser(registerUser);
            if (userDTO != null) {
                return new ResponseEntity<ApiResponse<UserDTO>>(new ApiResponse<UserDTO>("Succcessfully registered user", userDTO, true, "000"),HttpStatus.CREATED);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return  ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong. Please try again later.");
        
    }
    

}
