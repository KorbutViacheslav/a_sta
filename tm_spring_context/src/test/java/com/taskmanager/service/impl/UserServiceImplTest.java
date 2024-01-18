package com.taskmanager.service.impl;

import com.taskmanager.config.ConfigApp;
import com.taskmanager.model.User;
import com.taskmanager.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private static UserService userService;

    @BeforeAll
    public static void setUp() {
        var context = new AnnotationConfigApplicationContext(ConfigApp.class);
        userService = context.getBean(UserService.class);
        context.close();
    }

    public User createDefaultUser() {
        return new User("Jack", "Smith", "jack.smith@gmail.com", "password", new ArrayList<>());

    }

    public User createSecondUser() {
        return new User("Emma", "Davis", "emma.davis@gmail.com", "password", new ArrayList<>());
    }

    @AfterEach
    public void clearService() {
        userService.getAll().clear();
    }

    @Test
    public void addUser() {
        User newUser = createDefaultUser();

        User addedUser = userService.addUser(newUser);

        assertNotNull(addedUser);
        assertEquals(newUser, addedUser);
    }

    @Test
    public void addUserWithDuplicateEmail() {
        User user1 = createDefaultUser();
        userService.addUser(user1);

        User user2 = createDefaultUser();

        assertThrows(IllegalArgumentException.class, () -> userService.addUser(user2));
    }

    @Test
    public void updateUser() {
        User user = createDefaultUser();
        userService.addUser(user);
        user = createDefaultUser();
        user.setFirstName("TEST");

        User updatedUser = userService.updateUser(user);

        assertNotNull(updatedUser);
        assertEquals(user, updatedUser);
    }

    @Test
    public void updateUserWithNonExistingEmail() {
        User user = createDefaultUser();

        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(user));
    }

    @Test
    public void deleteUser() {
        User user = createDefaultUser();
        userService.addUser(user);

        userService.deleteUser(user);

        assertTrue(userService.getAll().isEmpty());
    }

    @Test
    public void deleteUserWithNullUser() {
        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(null));
    }

    @Test
    public void getAll() {
        User user1 = createDefaultUser();
        User user2 = createSecondUser();

        userService.addUser(user1);
        userService.addUser(user2);

        List<User> allUsers = userService.getAll();

        assertEquals(2, allUsers.size());
        assertTrue(allUsers.contains(user1));
        assertTrue(allUsers.contains(user2));
    }
}