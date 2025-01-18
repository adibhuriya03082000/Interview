package com.interview.subscription.Entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    
    private Long id;
    private String name;
    private String email;
    private String address;
    private String subscriptionKey; 
    private Date dateOfCreation;

}
