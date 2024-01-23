package com.taskmanager.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ToDoTest {
    private static ToDo validToDo;

    @BeforeEach
    void init() {
        validToDo = new ToDo();
        validToDo.setTitle("Sample ToDo");
        validToDo.setCreatedAt(LocalDateTime.now());

        User user = new User();
        user.setFirstName("Jon");
        user.setLastName("Jonson");
        user.setEmail("jonson@gmail.com");
        user.setPassword("Aa23123!");

        Role userRole = new Role();
        userRole.setName("USER");
        user.setRole(userRole);

        validToDo.setOwner(user);
    }

    @ParameterizedTest
    @CsvSource({
            "Sample ToDo, true",
            "ToDo #1, true",
            ", false"
    })
    void validateTitle(String title, boolean isValid) {
        validToDo.setTitle(title);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(validToDo);

        if (isValid) {
            assertEquals(0, violations.size(), "Validation failed for valid title: " + title + ". Violations: " + violations);
        } else {
            assertEquals(1, violations.size(), "Validation succeeded for invalid title: " + title + ". Violations: " + violations);
        }
    }
}