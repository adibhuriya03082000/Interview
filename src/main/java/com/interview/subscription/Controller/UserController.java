package com.interview.subscription.Controller;

import org.springframework.web.bind.annotation.RestController;
import com.interview.subscription.Service.UserService;
import com.interview.subscription.Utils.JwtUtil;

import lombok.AllArgsConstructor;

import com.interview.subscription.Entity.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        try {
            String token = userService.authenticate(user);
            response.put("status", "success");
            response.put("message", "Login successful");
            response.put("token", "Bearer " + token);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("status", "failure");
            response.put("message", e.getMessage());
            return ResponseEntity.status(401).body(response);
        }
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> adminEndpoint() {
        return ResponseEntity.ok("Admin endpoint accessed");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<String> userEndpoint() {
        return ResponseEntity.ok("User endpoint accessed");
    }
    
}
