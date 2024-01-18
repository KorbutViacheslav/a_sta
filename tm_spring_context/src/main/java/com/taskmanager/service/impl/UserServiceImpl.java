package com.taskmanager.service.impl;

import com.taskmanager.model.User;
import com.taskmanager.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private List<User> users = new ArrayList<>();

    @Override
    public User addUser(User user) {
        if (user != null && isEmailUnique(user.getEmail())) {
            users.add(user);
            return user;
        } else {
            throw new IllegalArgumentException("User cannot be null");
        }
    }
    private boolean isEmailUnique(String email) {
        return users.stream().noneMatch(existingUser -> existingUser.getEmail().equals(email));
    }

    @Override
    public User updateUser(User user) {
        if (user != null) {
            String emailToUpdate = user.getEmail();
            for (User updatedUser : users) {
                if (updatedUser.getEmail().equals(emailToUpdate)) {
                    updatedUser.setFirstName(updatedUser.getFirstName());
                    updatedUser.setLastName(updatedUser.getLastName());
                    updatedUser.setPassword(updatedUser.getPassword());
                    return updatedUser;
                }
            }
            throw new IllegalArgumentException("User with email " + emailToUpdate + " not found");
        } else {
            throw new IllegalArgumentException("Updated user cannot be null");
        }
    }

    @Override
    public void deleteUser(User user) {
        if (user != null) {
           users.remove(user);
        } else {
            throw new IllegalArgumentException("User cannot be null");
        }
    }

    @Override
    public List<User> getAll() {
        return users;
    }
}
