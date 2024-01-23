package com.taskmanager.service;


import com.taskmanager.model.Role;
import com.taskmanager.model.User;
import com.taskmanager.repository.UserRepository;
import com.taskmanager.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void create() {
        User user = new User(1L, "Jon", "Jonson",
                "jonson@gmail.com", "Aa23123!@#",
                new Role(), new ArrayList<>(), new ArrayList<>());

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.create(user);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getFirstName()).isEqualTo("Jon");

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void readById() {
        long userId = 1L;
        User user = new User(userId, "Jon", "Jonson",
                "jonson@gmail.com", "Aa23123!@#",
                new Role(), new ArrayList<>(), new ArrayList<>());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.readById(userId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(userId);
        assertThat(result.getFirstName()).isEqualTo("Jon");

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void update() {
        long userId = 1L;
        User existingUser = new User(userId, "Jon", "Jonson",
                "jonson@gmail.com", "Aa23123!@#",
                new Role(), new ArrayList<>(), new ArrayList<>());
        User updatedUser = new User(userId, "Updated", "Jonson",
                "jonson@gmail.com", "Aa23123!@#",
                new Role(), new ArrayList<>(), new ArrayList<>());

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(updatedUser);

        User result = userService.update(updatedUser);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(userId);
        assertThat(result.getFirstName()).isEqualTo("Updated");

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void delete() {
        long userId = 1L;
        User user = new User(userId, "Jon", "Jonson",
                "jonson@gmail.com", "Aa23123!@#",
                new Role(), new ArrayList<>(), new ArrayList<>());

        UserRepository mockedRepository = Mockito.mock(UserRepository.class);


        when(mockedRepository.findById(userId)).thenReturn(Optional.of(user));
        doNothing().when(mockedRepository).delete(user);


        UserService userService = new UserServiceImpl(mockedRepository);
        userService.delete(userId);


        verify(mockedRepository, times(1)).findById(userId);
        verify(mockedRepository, times(1)).delete(user);
    }

    @Test
    void getAll() {
        List<User> allUsers = Arrays.asList(
                new User(1L, "Jon", "Jonson",
                        "jonson@gmail.com", "Aa23123!@#",
                        new Role(), new ArrayList<>(), new ArrayList<>()),
                new User(2L, "Jon", "Jonson",
                        "jonson2@gmail.com", "Aa23123!@#",
                        new Role(), new ArrayList<>(), new ArrayList<>())
        );

        when(userRepository.findAll()).thenReturn(allUsers);

        List<User> result = userService.getAll();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void readByEmail() {
        String email = "jonson@gmail.com";
        User user = new User(1L, "Jon", "Jonson",
                email, "Aa23123!@#",
                new Role(), new ArrayList<>(), new ArrayList<>());

        when(userRepository.findByEmail(email)).thenReturn(user);

        User result = userService.readByEmail(email);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);

        verify(userRepository, times(1)).findByEmail(email);
    }

}