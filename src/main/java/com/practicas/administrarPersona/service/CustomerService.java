package com.practicas.administrarPersona.service;

import com.practicas.administrarPersona.model.Customer;
import com.practicas.administrarPersona.util.Util;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class CustomerService
 */

@Service
public class CustomerService {

    /**
     * Method allCuctomers to retrevie all customers in file.
     *
     * @param fileName name of file .txt to consult.
     * @return a list of all customers.
     */
    public Set<Customer> allCustomers(String fileName){
        Util.chooseFile(fileName);
        return Util.readFile();
    }

    /**
     * Method createCustomer to save and create a customer in the file.
     *
     * @param customer Object customer that will be created and save.
     * @param fileName name of file .txt to write the customer.
     * @return a customer Object with the data saved.
     */
    public Customer createCustomer(Customer customer, String fileName){
        Util.chooseFile(fileName);
        return Util.writeFile(customer);
    }

    /**
     * Method deleteCustomer to delete the customer by name.
     *
     * @param name name of customer that will be deleted.
     * @param fileName name of file .txt to update without customer deleted.
     */
    public void deleteCustomer(String name, String fileName){
        Set<Customer> customers = allCustomers(fileName);
        customers.removeIf(custom -> custom.getName().equalsIgnoreCase(name));
        Util.updateFile(customers);
    }

    /**
     * Method updateCustomer to update the data of customers.
     *
     * @param customer Objecto customer with new data updated.
     * @param fileName name of file .txr to update with new data.
     */
    public void updateCustomer(Customer customer, String fileName) {
        Set<Customer> customers = allCustomers(fileName);
        customers =
                customers.stream()
                .map(custom -> custom.getName().equalsIgnoreCase(customer.getName()) ? customer : custom)
                .collect(Collectors.toSet());
        Util.updateFile(customers);
    }

    /**
     * Method getCustomerByName to retrevie the data of customers by name.
     *
     * @param name name of the customer to consult.
     * @param fileName name of file .txt to consult.
     * @return a customer Object with the data.
     */
    public Customer getCustomerByName(String name, String fileName){
        Set<Customer> customers = allCustomers(fileName);
        return customers
                .stream()
                .filter(custom -> custom.getName().trim().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
