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
import static org.mockito.Mockito.doNothing;

@SpringBootTest
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

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
        customer.setBirthday(LocalDate.ofEpochDay(2000-03-27));

        Mockito.when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.createCustomer(customer);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    public void getCustomerByNameTest() {
        Customer customer = new Customer();
        customer.setName("Octavio Cardona");
        customer.setAge(24);
        customer.setBirthday(LocalDate.parse("2000-03-27"));

        Mockito.when(customerService.getCustomerByName(customer.getName())).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.getCustomerByName(customer.getName());

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

        Mockito.when(customerService.allCustomers()).thenReturn(customers);
        ResponseEntity<Set<Customer>> response = customerController.getAllCustomer();

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(customers, response.getBody());
    }

    @Test
    public void deleteCustomerTest(){
        doNothing().when(customerService).deleteCustomer("Octavio Cardona");

        ResponseEntity<String> response = customerController.deleteCustomer("Octavio Cardona");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("The operatiton was successful", response.getBody());
    }

    @Test
    public void updateCustomerTest() {
        Customer customer = new Customer();
        customer.setName("Octavio Cardona");
        customer.setAge(24);
        customer.setBirthday(LocalDate.parse("2000-03-27"));

        doNothing().when(customerService).updateCustomer(any(Customer.class));

        ResponseEntity<String> response = customerController.updateCustomer(customer);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("The operatiton was successful", response.getBody());
    }
}
