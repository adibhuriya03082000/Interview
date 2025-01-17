package com.interview.subscription.Repository;

import com.interview.subscription.Entity.User;

public interface UserRepository {
    
    public User findByUsername(String username);

    public User save(User user);
}
