package com.interview.subscription.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.subscription.Entity.Company;
import com.interview.subscription.Entity.User;
import com.interview.subscription.Service.SubscriptionService;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final PasswordEncoder passwordEncoder;

    public SubscriptionController(SubscriptionService subscriptionService, PasswordEncoder passwordEncoder) {
        this.subscriptionService = subscriptionService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/createCompany")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        return ResponseEntity.ok(subscriptionService.createCompany(company));
    }

    @PostMapping("/decode")
    public ResponseEntity<Company> decodeKey(@RequestBody String key) {
        return ResponseEntity.ok(subscriptionService.decodeKey(key));
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(subscriptionService.createUser(user));
    }
    
}
