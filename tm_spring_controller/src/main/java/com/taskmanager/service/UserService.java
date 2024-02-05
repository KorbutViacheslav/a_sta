package com.taskmanager.service;


import com.taskmanager.model.User;

import java.util.List;

public interface UserService {
    User create(User user);

    User readById(long id);

    User update(User user);

    void delete(long id);

    List<User> getAll();

    User readByEmail(String email);
}
