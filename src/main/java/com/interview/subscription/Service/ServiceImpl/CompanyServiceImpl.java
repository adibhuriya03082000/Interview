package com.interview.subscription.Service.ServiceImpl;

import java.util.Base64;

import org.springframework.stereotype.Service;

import com.interview.subscription.Entity.Company;
import com.interview.subscription.Repository.CompanyRepository;
import com.interview.subscription.Service.CompanyService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public String createCompany(Company company) {
        String key = Base64.getEncoder().encodeToString(
            (company.getName() + ":" + company.getEmail()).getBytes()
        );
        company.setSubscriptionKey(key);
        companyRepository.createCompany(company);
        return key;
    }

    @Override
    public Company decodeKey(String subscriptionKey) {
        String decoded = new String(Base64.getDecoder().decode(subscriptionKey));
        String[] parts = decoded.split(":");
        String name = parts[0];
        String email = parts[1];
        return companyRepository.findByNameAndEmail(name, email);
    }
    
}
