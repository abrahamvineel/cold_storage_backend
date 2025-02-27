package org.userservice.controller;

import jakarta.ws.rs.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.userservice.dto.UserCreationRequest;
import org.userservice.dto.UserLoginDetailsRequest;
import org.userservice.model.User;
import org.userservice.service.UserService;
import org.userservice.util.AuthResponse;
import org.userservice.util.JwtUtil;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserCreationRequest userCreationRequest) {
        userService.createUser(userCreationRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDetailsRequest request) {
        User user = userService.findByEmail(request.getEmail());

        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(401).body("Invalid Credentials");
    }

    @PostMapping("/validate-token")
    public boolean isValidToken(@RequestParam("email") String userEmail) {
        return jwtUtil.validateToken(userEmail);
    }
}
