package com.practicas.administrarPersona.service;

import com.practicas.administrarPersona.model.Customer;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final Path pathFile = Path.of("customers.txt");

    public Set<Customer> allCustomers(){
        Set<Customer> customers = new HashSet<>();
        try{
            List<String> allLines= Files.readAllLines(pathFile);
            for (String line : allLines) {
                String [] data = line.split(",");
                Customer customer = new Customer();
                customer.setName(data[0]);
                customer.setAge(Integer.parseInt(data[1]));
                customer.setBirthday(LocalDate.parse(data[2]));
                customers.add(customer);
            }
            return customers;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createCustomer(Customer customer){
        try(FileWriter writer = new FileWriter(pathFile.toFile(),true)){
           writer.write(customer.toString());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteCustomer(String name){
        Set<Customer> customers = allCustomers();
        customers.removeIf(custom -> custom.getName().equals(name));
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathFile.toUri()))) {
            for (Customer customer : customers) {
                writer.write(customer.toString());
            }
        } catch (IOException eX) {
            throw new RuntimeException(eX);
        }
    }

    public void updateCustomer(Customer customer) {
        Set<Customer> customers = allCustomers();
        Set<Customer> updateCustomers =
                customers.stream()
                .map(custom -> custom.getName().equalsIgnoreCase(customer.getName()) ? customer : custom)
                .collect(Collectors.toSet());

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathFile.toUri()))){
            for (Customer updateCustomer : updateCustomers){
                writer.write(updateCustomer.toString());
            }
        } catch(IOException ex){
            throw new RuntimeException(ex);
        }
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
