package com.interview.subscription.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interview.subscription.Entity.Company;
import com.interview.subscription.Service.CompanyService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/createcompany")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<Map<String, Object>> createCompany(@RequestBody Company company) {
        String key = companyService.createCompany(company);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Company created successfully");
        response.put("subscriptionKey", key);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/decode")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<Company> decodeKey(@RequestParam("subscriptionkey") String subscriptionkey) {
        Company company = companyService.decodeKey(subscriptionkey);
        return ResponseEntity.ok(company);
    }
    
}
