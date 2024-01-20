package com.taskmanager.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserTest {
    private static User validUser;

    @BeforeEach
    void init() {
        validUser = new User();

        Role userRole = new Role();
        userRole.setName("USER");
        validUser.setRole(userRole);

        validUser.setFirstName("Jon");
        validUser.setLastName("Jonson");
        validUser.setEmail("jonson@gmail.com");
        validUser.setPassword("Aa23123!@#");
    }

    @ParameterizedTest
    @CsvSource({
            "John, true",
            "Alice-Wonderland, true",
            "JonsonSmith, true",
            "john, false",
            "john-doe, false",
            "123Doe, false"
    })
    void validateFirstName(String firstName, boolean isValid) {
        validUser.setFirstName(firstName);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);

        if (isValid) {
            assertEquals(0, violations.size(), "Validation failed for valid first name: " + firstName + ". Violations: " + violations);
        } else {
            assertEquals(1, violations.size(), "Validation succeeded for invalid first name: " + firstName + ". Violations: " + violations);
        }
    }

/*    @ParameterizedTest
    @CsvSource({
            "John, true",
            "Alice-Wonderland, true",
            "JonsonSmith, true",
            "john, false",
            "john-doe, false",
            "123Doe, false"
    })
    void validateLastName(String lastName, boolean isValid) {
        validUser.setLastName(lastName);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);

        if (isValid) {
            assertEquals(0, violations.size(), "Validation failed for valid last name: " + lastName);
        } else {
            assertEquals(1, violations.size(), "Validation succeeded for invalid last name: " + lastName);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "valid@example.com, true",
            "user@domain.com, true",
            "invalid-email, false",
            "user@.com, false",
            "@domain.com, false",
            "user@domain..com, false"
    })
    void validateEmail(String email, boolean isValid) {
        validUser.setEmail(email);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);

        if (isValid) {
            assertEquals(0, violations.size(), "Validation failed for valid email: " + email);
        } else {
            assertEquals(1, violations.size(), "Validation succeeded for invalid email: " + email);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "ValidPassword123!, true",
            "SecureP@ssw0rd, true",
            "WeakPass, false",
            "NoSpecialChars123, false",
            "NoUppercaseletters!@#, false",
            "NoDigitsSpecialChars, false"
    })
    void validatePassword(String password, boolean isValid) {
        validUser.setPassword(password);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);

        if (isValid) {
            assertEquals(0, violations.size(), "Validation failed for valid password: " + password);
        } else {
            assertEquals(1, violations.size(), "Validation succeeded for invalid password: " + password);
        }
    }*/


}