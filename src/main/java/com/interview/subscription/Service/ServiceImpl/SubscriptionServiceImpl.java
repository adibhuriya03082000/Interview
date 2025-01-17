package com.interview.subscription.Service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.interview.subscription.Entity.Company;
import com.interview.subscription.Entity.User;
import com.interview.subscription.Repository.CompanyRepository;
import com.interview.subscription.Repository.UserRepository;
import com.interview.subscription.Service.SubscriptionService;
import com.interview.subscription.Utils.JwtUtil;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public SubscriptionServiceImpl(CompanyRepository companyRepository, UserRepository userRepository, JwtUtil jwtUtil) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Company createCompany(Company company) {
        String key = jwtUtil.generateToken(company.getName());
        company.setSubscriptionKey(key);
        companyRepository.saveCompany(company);

        return company;
    }

    @Override
    public Company decodeKey(String key) {
        String companyName = jwtUtil.extractUsername(key);
        Company company = new Company();
        company.setName(companyName);

        return company;
    }

    @Override
    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }
}
