package com.interview.subscription.Entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

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
public class User {
    
    @Id
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String mobileno;
    private String username;
    private String password;
    private String role;
    private Date dateOfCreation;
    private Date dateOfModification;
}
