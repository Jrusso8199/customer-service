package com.example.customerservice;

import com.example.customerservice.controller.CustomerController;
import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.CustomerRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

public abstract class BaseContractTest {

    @BeforeEach
    public void setup() {
        CustomerRepository repository = Mockito.mock(CustomerRepository.class);
        CustomerController controller = new CustomerController(repository);

        // Set up test data
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPassword("password123");

        Mockito.when(repository.save(Mockito.any(Customer.class))).thenReturn(customer);

        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(controller);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }
}
