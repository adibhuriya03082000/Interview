package com.interview.subscription.Repository.RepositoryImpl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.interview.subscription.Entity.User;
import com.interview.subscription.Repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO `user` (firstname, lastname, email, mobileno, username, password, role, dateOfCreation) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
        jdbcTemplate.update(sql, 
            user.getFirstname(), 
            user.getLastname(), 
            user.getEmail(), 
            user.getMobileno(), 
            user.getUsername(), 
            user.getPassword(), 
            user.getRole());
        return user;
    }
       
}
