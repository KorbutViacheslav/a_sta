package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.model.ToDo;
import com.taskmanager.model.User;

import java.util.List;

public interface TaskService {
    Task addTask(Task task, ToDo todo);
    Task updateTask(Task task);
    void deleteTask(Task task);
    List<Task> getAll();
    List<Task> getByToDo(ToDo todo);
    Task getByToDoName(ToDo todo, String name);
    Task getByUserName(User user, String name);
}
