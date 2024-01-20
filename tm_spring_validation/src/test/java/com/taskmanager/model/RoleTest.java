package com.taskmanager.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoleTest {
    private static Role superAdminRole;

    @BeforeEach
     void init(){
        superAdminRole = new Role();
        superAdminRole.setName("SUPER-ADMIN");
    }



    @ParameterizedTest
    @CsvSource({
            "SUPER-ADMIN, true",
            "ADMIN, true",
            "MODERATOR, true",
            ", false",
            " , false"
    })
    void validateRoleName(String roleName, boolean isValid) {
        superAdminRole.setName(roleName);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Role>> violations = validator.validate(superAdminRole);

        if (isValid) {
            assertEquals(0, violations.size(), "Validation failed for valid role name: " + roleName);
        } else {
            assertEquals(1, violations.size(), "Validation succeeded for invalid role name: " + roleName);
        }
    }


}