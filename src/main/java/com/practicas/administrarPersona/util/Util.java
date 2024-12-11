package com.practicas.administrarPersona.util;

import com.practicas.administrarPersona.model.Customer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Util {

    private static final Path pathFile = Paths.get("customers.txt");

    public static Set<Customer> readFile() {
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

    public static Customer writeFile (Customer customer) {
        try(FileWriter writer = new FileWriter(pathFile.toFile(),true)){
            writer.write(customer.toString());
            return customer;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void updateFile (Set<Customer> customers) {
        try(BufferedWriter writer = Files.newBufferedWriter(pathFile)) {
            for (Customer customer : customers) {
                writer.write(customer.toString());
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
