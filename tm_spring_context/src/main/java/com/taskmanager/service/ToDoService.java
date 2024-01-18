package com.taskmanager.service;

import com.taskmanager.model.ToDo;
import com.taskmanager.model.User;

import java.util.List;

public interface ToDoService {
    ToDo addTodo(ToDo todo, User user);
    ToDo updateTodo(ToDo todo);
    void deleteTodo(ToDo todo);
    List<ToDo> getAll();
    List<ToDo> getByUser(User user);
    ToDo getByUserTitle(User user, String title);
}
