package com.interview.subscription.Service;

import com.interview.subscription.Entity.Company;
import com.interview.subscription.Entity.User;

public interface SubscriptionService {
    
    public Company createCompany(Company company);
    public Company decodeKey(String key);
    public User createUser(User user);
}
