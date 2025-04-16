package com.example.customerservice.model;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Validator validator;
    private Customer customer;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        customer = new Customer();
    }

    @Test
    void whenAllFieldsAreValid_thenNoValidationViolations() {
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPassword("password123");

        var violations = validator.validate(customer);
        assertTrue(violations.isEmpty());
    }

    @Test
    void whenFirstNameIsNull_thenValidationViolation() {
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPassword("password123");

        var violations = validator.validate(customer);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("First name is required", violations.iterator().next().getMessage());
    }

    @Test
    void whenEmailIsInvalid_thenValidationViolation() {
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("invalid-email");
        customer.setPassword("password123");

        var violations = validator.validate(customer);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Invalid email format", violations.iterator().next().getMessage());
    }
}
