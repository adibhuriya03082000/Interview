package com.interview.subscription.Repository;

import com.interview.subscription.Entity.Company;

public interface CompanyRepository {

    public void createCompany(Company company);

    public Company findByNameAndEmail(String name, String email);

    
    
}
