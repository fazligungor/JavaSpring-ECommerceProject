package com.works.services;

import com.works.entities.Customer;
import com.works.entities.CustomerPassword;
import com.works.repositories.CustomerRepository;
import com.works.utils.ERest;
import com.works.utils.Util;
import org.springframework.boot.env.RandomValuePropertySourceEnvironmentPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity createAccount(Customer customer) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            String newPass = Util.md5(customer.getPassword());
            customer.setPassword(newPass);
            customerRepository.save(customer);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Success sign-up");
            hm.put(ERest.result, customer);
        }catch (Exception e){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Fail sign-up");
            return new ResponseEntity(hm, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity deleteAccount(String email, String password) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        String newPassword = Util.md5(password);
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCaseAndPasswordEquals(email, newPassword);
        if (optionalCustomer.isPresent()) {
            customerRepository.delete(optionalCustomer.get());
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Delete Success");
            return new ResponseEntity(hm, HttpStatus.OK);
        }
        return new ResponseEntity(hm, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity forgotPassword(CustomerPassword customerPassword) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsAndTcEquals(customerPassword.getEmail(), customerPassword.getTc());
        if (optionalCustomer.isPresent()) {
            Customer dbCustomer = optionalCustomer.get();
            String newPassword = Util.md5(customerPassword.getNewPassword());
            dbCustomer.setPassword(newPassword);
            customerRepository.saveAndFlush(dbCustomer);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "password updated");
            return new ResponseEntity(hm, HttpStatus.OK);
        } else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Email or TC not found");
            return new ResponseEntity(hm, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity login(Customer customer) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        String newPassword = Util.md5(customer.getPassword());
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCaseAndPasswordEquals(customer.getEmail(), newPassword);
        if (optionalCustomer.isPresent()) {
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Login Success");
            return new ResponseEntity(hm, HttpStatus.OK);
        }
        return new ResponseEntity(hm, HttpStatus.NOT_FOUND);
    }
}
