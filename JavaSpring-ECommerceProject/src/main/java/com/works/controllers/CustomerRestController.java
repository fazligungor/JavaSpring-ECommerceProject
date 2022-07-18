package com.works.controllers;

import com.works.entities.Customer;
import com.works.entities.CustomerPassword;
import com.works.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerRestController {
    final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody Customer customer){

        return customerService.createAccount(customer);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Customer customer){
        return customerService.login(customer);
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity forgotPassword(@RequestBody CustomerPassword customerPassword){
        return customerService.forgotPassword(customerPassword);
    }

    @DeleteMapping("/deletecustomer")
    public ResponseEntity deleteCustomer(@RequestBody @NotNull String email,
                                         @NotNull String password){
        return customerService.deleteAccount(email, password);
    }
}
