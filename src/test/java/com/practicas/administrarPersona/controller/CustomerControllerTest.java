package com.practicas.administrarPersona.controller;

import com.practicas.administrarPersona.model.Customer;
import com.practicas.administrarPersona.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    private final String fileName = "customer.txt";

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        customerController = new CustomerController(customerService);
    }

    @Test
    public void createCustomerTest(){
        Customer customer = new Customer();
        customer.setName("Octavio Cardona");
        customer.setAge(24);
        customer.setBirthday(LocalDate.ofEpochDay(2000-3-27));

        Mockito.when(customerService.createCustomer(any(Customer.class),eq(fileName))).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.createCustomer(fileName, customer);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    public void getCustomerByNameTest() {
        Customer customer = new Customer();
        customer.setName("Octavio Cardona");
        customer.setAge(24);
        customer.setBirthday(LocalDate.parse("2000-03-27"));

        Mockito.when(customerService.getCustomerByName(customer.getName(), fileName)).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.getCustomerByName(fileName, customer.getName());

        assertEquals(HttpStatusCode.valueOf(200),response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    public void getAllCustomersTest() {
        Set<Customer> customers = new HashSet<>();

        Customer customer = new Customer();
        customer.setName("Octavio Cardona");
        customer.setAge(24);
        customer.setBirthday(LocalDate.parse("2000-03-27"));

        Customer customer1 = new Customer();
        customer1.setName("Rafa Aldana");
        customer1.setAge(27);
        customer1.setBirthday(LocalDate.parse("1996-08-16"));

        customers.add(customer);
        customers.add(customer1);

        Mockito.when(customerService.allCustomers(fileName)).thenReturn(customers);
        ResponseEntity<Set<Customer>> response = customerController.getAllCustomer(fileName);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(customers, response.getBody());
    }

    @Test
    public void deleteCustomerTest(){
        doNothing().when(customerService).deleteCustomer("Octavio Cardona", "customers.txt");

        ResponseEntity<String> response = customerController.deleteCustomer(fileName,"Octavio Cardona");

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("The operatiton was successful", response.getBody());
    }

    @Test
    public void updateCustomerTest() {
        Customer customer = new Customer();
        customer.setName("Octavio Cardona");
        customer.setAge(24);
        customer.setBirthday(LocalDate.parse("2000-03-27"));

        doNothing().when(customerService).updateCustomer(any(Customer.class), eq(fileName));

        ResponseEntity<String> response = customerController.updateCustomer(fileName, customer);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("The operatiton was successful", response.getBody());
    }
}
