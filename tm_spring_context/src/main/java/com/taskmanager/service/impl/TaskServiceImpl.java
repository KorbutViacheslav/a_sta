package com.taskmanager.service.impl;

import com.taskmanager.model.Task;
import com.taskmanager.model.ToDo;
import com.taskmanager.model.User;
import com.taskmanager.service.TaskService;
import com.taskmanager.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final ToDoService toDoService;

    // TODO: implement method
    @Override
    public Task addTask(Task task, ToDo todo) {
        return null;
    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }

    @Override
    public void deleteTask(Task task) {

    }

    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public List<Task> getByToDo(ToDo todo) {
        return null;
    }

    @Override
    public Task getByToDoName(ToDo todo, String name) {
        return null;
    }

    @Override
    public Task getByUserName(User user, String name) {
        return null;
    }

}
