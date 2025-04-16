package com.example.customerservice;

import com.example.customerservice.controller.CustomerController;
import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.CustomerRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.Arrays;

public abstract class BaseContractTest {

    private CustomerRepository customerRepository;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        RestAssuredMockMvc.standaloneSetup(new CustomerController(customerRepository));

        customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPassword("password123");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setEmail("jane.doe@example.com");
        customer2.setPassword("password456");

        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        Mockito.when(customerRepository.findAll()).thenReturn(Arrays.asList(customer, customer2));
    }
}
