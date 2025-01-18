package com.interview.subscription.Service;

import com.interview.subscription.Entity.Company;

public interface CompanyService {

    public String createCompany(Company company);

    public Company decodeKey(String subscriptionKey);
    
}
