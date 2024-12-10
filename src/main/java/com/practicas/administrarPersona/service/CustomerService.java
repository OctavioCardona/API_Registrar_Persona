package com.practicas.administrarPersona.service;

import com.practicas.administrarPersona.model.Customer;
import com.practicas.administrarPersona.util.Util;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    public Set<Customer> allCustomers(){
        return Util.readFile();
    }

    public void createCustomer(Customer customer){
        Util.writeFile(customer);
    }

    public void deleteCustomer(String name){
        Set<Customer> customers = allCustomers();
        customers.removeIf(custom -> custom.getName().equalsIgnoreCase(name));
        Util.updateFile(customers);
    }

    public void updateCustomer(Customer customer) {
        Set<Customer> customers = allCustomers();
        Set<Customer> updateCustomers =
                customers.stream()
                .map(custom -> custom.getName().equalsIgnoreCase(customer.getName()) ? customer : custom)
                .collect(Collectors.toSet());
        Util.updateFile(updateCustomers);
    }

    public Customer getCustomerByName(String name){
        Set<Customer> customers = allCustomers();
        return customers
                .stream()
                .filter(custom -> custom.getName().trim().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
