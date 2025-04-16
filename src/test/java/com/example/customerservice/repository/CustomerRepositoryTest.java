package com.example.customerservice.repository;

import com.example.customerservice.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void whenSaveCustomer_thenCustomerIsPersisted() {
        // Create a customer
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPassword("password123");

        // Save the customer
        Customer savedCustomer = customerRepository.save(customer);

        // Flush the changes to the database
        entityManager.flush();
        
        // Clear the persistence context
        entityManager.clear();

        // Retrieve the customer from the database
        Customer foundCustomer = customerRepository.findById(savedCustomer.getId()).orElse(null);

        // Verify the customer was saved correctly
        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(foundCustomer.getLastName()).isEqualTo(customer.getLastName());
        assertThat(foundCustomer.getEmail()).isEqualTo(customer.getEmail());
        assertThat(foundCustomer.getPassword()).isEqualTo(customer.getPassword());
    }
}
