package com.interview.subscription.Entity;

import lombok.Data;
import lombok.Setter;
import lombok.Getter;

@Data
@Setter
@Getter
public class Company {
    
    private Long id;
    private String name;
    private String email;
    private String subscriptionKey; 

}
