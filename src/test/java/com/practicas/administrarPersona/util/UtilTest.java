package com.practicas.administrarPersona.util;

import com.practicas.administrarPersona.model.Customer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UtilTest {

    private static Path fileTest;

    private Customer customer;


    @BeforeAll
    public static void steUpAll() throws IOException {
        fileTest = Files.createTempFile("customersTest", ".txt");
        Util.chooseFile(fileTest.toString());
    }

    @AfterAll
    public static void deleteFile() throws IOException {
        Files.delete(fileTest);
    }

    @BeforeEach
    public void setUp() throws IOException {
        Files.write(fileTest, "".getBytes());
        customer = new Customer("Octavio Cardona", 24, LocalDate.of(2000, 3,27));
    }

    @Test
    public void readAndWriteFileTest() {

        Customer customerWrite = Util.writeFile(customer);
        Set<Customer> customersRead = Util.readFile();

        assertEquals(customerWrite, customer);
        assertTrue(customersRead
                .stream()
                .allMatch(custom ->
                        custom.getName().equalsIgnoreCase(customer.getName()) &&
                        custom.getAge().equals(customer.getAge()) &&
                        custom.getBirthday().equals(customer.getBirthday())
                )) ;
    }

    @Test
    public void updateFileTest(){
        Customer customer2 = new Customer("Pepe Ramirez",32,LocalDate.of(1992,2,20));
        Customer updateCustomer = new Customer("Pepe Ramirez",30,LocalDate.of(1990,2,20));

        Util.writeFile(customer);
        Util.writeFile(customer2);

        Set<Customer> updateCustomers = Set.of(customer, updateCustomer);
        Util.updateFile(updateCustomers);

        Set<Customer> readUpdateCustomers = Util.readFile();

        assertTrue(readUpdateCustomers
                .stream()
                .anyMatch(custom ->
                        custom.getName().equalsIgnoreCase(customer.getName()) &&
                                custom.getAge().equals(customer.getAge()) &&
                                custom.getBirthday().equals(customer.getBirthday())
                )) ;
        assertTrue(readUpdateCustomers
                .stream()
                .anyMatch(custom ->
                        custom.getName().equalsIgnoreCase(updateCustomer.getName()) &&
                                custom.getAge().equals(updateCustomer.getAge()) &&
                                custom.getBirthday().equals(updateCustomer.getBirthday())
                )) ;
    }

}
