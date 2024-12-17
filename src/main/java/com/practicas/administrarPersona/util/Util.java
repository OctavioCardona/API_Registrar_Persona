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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class Util with methods write, read and update in file.
 */
public class Util {

    /**
     * Static variable of Path where the file will be.
     */
    private static Path pathFile;

    /**
     * Method chooseFile to select the file to use.
     *
     * @param fileName the name of file to use.
     */
    public static void chooseFile(String fileName) {
        pathFile = Paths.get(fileName);
    }

    /**
     * Method readFile to read the file where going to work.
     *
     * @return a list of all customer inside the file.
     */
    public static Set<Customer> readFile() {
        Set<Customer> customers = new HashSet<>();
        try{
            customers = Files.readAllLines(pathFile).stream()
                    .map(line-> {
                        String[] data = line.split(",");
                        return new Customer(data[0],Integer.parseInt(data[1]),LocalDate.parse(data[2]));
                    }).collect(Collectors.toSet());
            return customers;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Method writeFile to write inside the file the customer data.
     *
     * @param customer object with data of customers.
     * @return a customer Object with his data.
     */
    public static Customer writeFile (Customer customer) {
        try(FileWriter writer = new FileWriter(pathFile.toFile(),true)){
            writer.write(customer.toString());
            return customer;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Method updateFile to update new data of customers.
     *
     * @param customers object with data of customers.
     */
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
