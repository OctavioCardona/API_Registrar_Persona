package com.practicas.administrarPersona.controller;

import com.practicas.administrarPersona.model.Customer;
import com.practicas.administrarPersona.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping
@Tag(name = "Maneja personal", description = "Operaciones para administrar personal")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Crea y guarda una persona")
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer createCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createCustomer,HttpStatus.OK);
    }

    @Operation(summary = "Obtiene por nombre la información de una persona")
    @GetMapping("/{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable String name) {
        Customer getCustomer = customerService.getCustomerByName(name);
        return new ResponseEntity<>(getCustomer,HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todo el personal registrado")
    @GetMapping("/")
    public ResponseEntity<Set<Customer>> getAllCustomer() {
        return new ResponseEntity<>(customerService.allCustomers(),HttpStatus.OK);
    }

    @Operation(summary = "Borra por nombre los datos de una persona")
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String name) {
        customerService.deleteCustomer(name);
        return ResponseEntity.ok("The operatiton was successful");
    }

    @Operation(summary = "Actualiza los datos de una persona")
    @PutMapping
    public ResponseEntity<String> updateCustomer (@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
        return ResponseEntity.ok("The operatiton was successful");
    }


}
