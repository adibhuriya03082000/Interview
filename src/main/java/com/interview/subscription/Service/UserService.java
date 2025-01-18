package com.interview.subscription.Service;

import com.interview.subscription.Entity.User;

public interface UserService {

    public User createUser(User user);

    public String authenticate(User user);

    public void saveLoginDetails(String username);
    
}
