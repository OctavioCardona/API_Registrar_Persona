package com.practicas.administrarPersona.controller;

import com.practicas.administrarPersona.model.Customer;
import com.practicas.administrarPersona.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer createCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createCustomer,HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable String name) {
        Customer getCustomer = customerService.getCustomerByName(name);
        return new ResponseEntity<>(getCustomer,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Set<Customer>> getAllCustomer() {
        return new ResponseEntity<>(customerService.allCustomers(),HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String name) {
        customerService.deleteCustomer(name);
        return ResponseEntity.ok("The operatiton was successful");
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer (@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
        return ResponseEntity.ok("The operatiton was successful");
    }


}
