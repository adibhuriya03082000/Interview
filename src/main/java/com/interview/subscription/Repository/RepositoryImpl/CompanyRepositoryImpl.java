package com.interview.subscription.Repository.RepositoryImpl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.interview.subscription.Entity.Company;
import com.interview.subscription.Repository.CompanyRepository;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {

    private final JdbcTemplate jdbcTemplate;

    public CompanyRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveCompany(Company company) {
        String sql = "INSERT INTO company (name, email, subscription_key) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, company.getName(), company.getEmail(), company.getSubscriptionKey());
    }

    @Override
    public Company findByKey(String key) {
       String sql = "SELECT * FROM company WHERE subscription_key = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Company.class), key);
    }
    
}
