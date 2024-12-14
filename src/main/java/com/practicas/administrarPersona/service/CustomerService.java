package com.practicas.administrarPersona.service;

import com.practicas.administrarPersona.model.Customer;
import com.practicas.administrarPersona.util.Util;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    public Set<Customer> allCustomers(String fileName){
        Util.chooseFile(fileName);
        return Util.readFile();
    }

    public Customer createCustomer(Customer customer, String fileName){
        Util.chooseFile(fileName);
        return Util.writeFile(customer);
    }

    public void deleteCustomer(String name, String fileName){
        Set<Customer> customers = allCustomers(fileName);
        customers.removeIf(custom -> custom.getName().equalsIgnoreCase(name));
        Util.updateFile(customers);
    }

    public void updateCustomer(Customer customer, String fileName) {
        Set<Customer> customers = allCustomers(fileName);
        customers =
                customers.stream()
                .map(custom -> custom.getName().equalsIgnoreCase(customer.getName()) ? customer : custom)
                .collect(Collectors.toSet());
        Util.updateFile(customers);
    }

    public Customer getCustomerByName(String name, String fileName){
        Set<Customer> customers = allCustomers(fileName);
        return customers
                .stream()
                .filter(custom -> custom.getName().trim().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
