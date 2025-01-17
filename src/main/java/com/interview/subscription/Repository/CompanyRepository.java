package com.interview.subscription.Repository;

import com.interview.subscription.Entity.Company;

public interface CompanyRepository {

    public void saveCompany(Company company);
    public Company findByKey(String key);
    
}
