package com.taskmanager.repository;

import com.taskmanager.model.Role;
import com.taskmanager.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByEmail() {
        Role userRole = new Role();
        userRole.setName("USER");
        testEntityManager.persistAndFlush(userRole);

        User user = new User();
        user.setFirstName("Jon");
        user.setLastName("Jonson");
        user.setEmail("jonson@gmail.com");
        user.setPassword("Aa23123!@#");
        user.setRole(userRole);
        testEntityManager.persistAndFlush(user);

        String userEmail = "jonson@gmail.com";
        User foundUser = userRepository.findByEmail(userEmail);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo(userEmail);
        assertThat(foundUser.getFirstName()).isEqualTo("Jon");
        assertThat(foundUser.getLastName()).isEqualTo("Jonson");

    }
}