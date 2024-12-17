package com.practicas.administrarPersona.controller;

import com.practicas.administrarPersona.model.Customer;
import com.practicas.administrarPersona.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

/**
 * Class controller with endpoitns for the API's.
 *
 *  @author Octavio Cardona.
 *
 */

@RestController
@RequestMapping
@Tag(name = "Manage customers", description = "Operations to manage customer")
public class CustomerController {

    /**
     * Instance of CustomerService
     */
    private final CustomerService customerService;

    /**
     * Consctructor CustomerController to use Ijecction of dependencys by cosntructor.
     *
     * @param customerService CustomerService injection.
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Method post to create and save persona.
     *
     * @param fileName as HttpHeader to select file name where search.
     * @param customer as RequestBody to save and create the object customer.
     * @return data of customer created.
     */
    @Operation(summary = "Create and save a customer")
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestHeader String fileName, @RequestBody Customer customer){
        Customer createCustomer = customerService.createCustomer(customer, fileName);
        return new ResponseEntity<>(createCustomer,HttpStatus.OK);
    }

    /**
     * Method get to obtain the customer using the name.
     *
     * @param fileName as HttpHeader to select file name where search.
     * @param name as PathVariable to search the customer by name.
     * @return data of customer searched.
     */
    @Operation(summary = "Gets information about a persona by name")
    @GetMapping("/{name}")
    public ResponseEntity<Customer> getCustomerByName(@RequestHeader String fileName, @PathVariable String name) {
        Customer getCustomer = customerService.getCustomerByName(name,fileName);
        return new ResponseEntity<>(getCustomer,HttpStatus.OK);
    }

    /**
     * Method get to obtain all customers.
     *
     * @param fileName as HttpHeader to select file name where search.
     * @return data of all customers.
     */
    @Operation(summary = "Get all registered customers")
    @GetMapping("/")
    public ResponseEntity<Set<Customer>> getAllCustomer(@RequestHeader String fileName) {
        return new ResponseEntity<>(customerService.allCustomers(fileName),HttpStatus.OK);
    }

    /**
     * Method delete to delete customers by name.
     *
     * @param fileName as HttpoHeader to select file name where search.
     * @param name as PathVariable to search in file the customer to delete.
     * @return a message of all OK.
     */
    @Operation(summary = "Delete by name the data of a customer")
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteCustomer(@RequestHeader String fileName, @PathVariable String name) {
        customerService.deleteCustomer(name,fileName);
        return ResponseEntity.ok("The operatiton was successful");
    }

    /**
     * Method update to update the data of customers.
     *
     * @param fileName as HttpHeader to select file name where search.
     * @param customer as RequestBody to update the new data.
     * @return a message of all ok.
     */
    @Operation(summary = "Update the data of customer")
    @PutMapping
    public ResponseEntity<String> updateCustomer (@RequestHeader String fileName, @RequestBody Customer customer) {
        customerService.updateCustomer(customer,fileName);
        return ResponseEntity.ok("The operatiton was successful");
    }
}
