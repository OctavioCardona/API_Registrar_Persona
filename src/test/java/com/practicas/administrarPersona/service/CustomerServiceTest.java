package com.practicas.administrarPersona.service;

import com.practicas.administrarPersona.model.Customer;
import com.practicas.administrarPersona.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
public class CustomerServiceTest {
    
    @InjectMocks
    private CustomerService customerService;
    
    @Mock
    private Util util;

    private Set<Customer> customers;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        customers = new HashSet<>();

        Customer customer = new Customer();
        customer.setName("Octavio Cardona");
        customer.setAge(24);
        customer.setBirthday(LocalDate.parse("2000-03-27"));

        Customer customer1 = new Customer();
        customer1.setName("Octavio Cardona");
        customer1.setAge(24);
        customer1.setBirthday(LocalDate.parse("2000-03-27"));

        customers.add(customer);
        customers.add(customer1);
    }

    @Test
    public void allCustomersTest() {
        try(MockedStatic<Util> util = mockStatic(Util.class)) {
            util.when(Util::readFile).thenReturn(customers);
            Set<Customer> customersReadFile = customerService.allCustomers();
            assertTrue(customersReadFile.containsAll(customers));
        }
    }

    @Test
    public void createCustomerTest() {
        Customer customer = new Customer();
        customer.setName("Octavio Cardona");
        customer.setAge(24);
        customer.setBirthday(LocalDate.parse("2000-03-27"));

        try(MockedStatic<Util> util = mockStatic(Util.class)) {
            util.when(() -> Util.writeFile(customer)).thenReturn(customer);
            Customer customerWrite = customerService.createCustomer(customer);
            assertEquals(customer, customerWrite);
        }
    }
}
