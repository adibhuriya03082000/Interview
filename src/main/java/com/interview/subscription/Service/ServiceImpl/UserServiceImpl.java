package com.interview.subscription.Service.ServiceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.interview.subscription.Entity.User;
import com.interview.subscription.Repository.UserRepository;
import com.interview.subscription.Service.UserService;
import com.interview.subscription.Utils.JwtUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public User createUser(User user) {
        userRepository.createUser(user);
        return user;
    }

    @Override
    public String authenticate(User user) {
        User storedUser = userRepository.findByUsername(user.getUsername());
        if (storedUser != null && passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {
            return jwtUtil.generateToken(storedUser.getUsername(), storedUser.getRole());
        }
        throw new RuntimeException("Invalid credentials");
    }

    @Override
    public void saveLoginDetails(String username) {
        userRepository.saveLoginDetails(username);
    }

    
}
