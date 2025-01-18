package com.interview.subscription.Repository.RepositoryImpl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.interview.subscription.Entity.Company;
import com.interview.subscription.Repository.CompanyRepository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void createCompany(Company company) {
        String sql = "INSERT INTO company (name, email, address, subscription_key, dateOfCreation) VALUES (?, ?, ?, ?, NOW())";
        jdbcTemplate.update(sql, company.getName(), company.getEmail(), company.getAddress(), company.getSubscriptionKey());
    }

    @Override
    public Company findByNameAndEmail(String name, String email) {
        String sql = "SELECT * FROM company WHERE name = ? AND email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name, email}, (rs, rowNum) -> {
            Company company = new Company();
            company.setId(rs.getLong("id"));
            company.setName(rs.getString("name"));
            company.setEmail(rs.getString("email"));
            company.setAddress(rs.getString("address"));
            company.setSubscriptionKey(rs.getString("subscription_key"));
            company.setDateOfCreation(rs.getDate("dateOfCreation"));
            return company;
        });
    }

}
