package com.interview.subscription.Repository.RepositoryImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.interview.subscription.Entity.User;
import com.interview.subscription.Repository.UserRepository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setFirstname(rs.getString("firstname"));
            user.setLastname(rs.getString("lastname"));
            user.setEmail(rs.getString("email"));
            user.setMobileno(rs.getString("mobileno"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            return user;
        });
    }

    @Override
    public User createUser(User user) {
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

    @Override
    public void saveLoginDetails(String username) {
        String sql = "INSERT INTO login (username, login_time) VALUES (?, CURRENT_TIMESTAMP)";
        jdbcTemplate.update(sql, username);
    }
       
}
