package com.taskmanager.service.impl;

import com.taskmanager.model.Task;
import com.taskmanager.model.ToDo;
import com.taskmanager.model.User;
import com.taskmanager.service.TaskService;
import com.taskmanager.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final ToDoService toDoService;

    @Override
    public Task addTask(Task task, ToDo todo) {
        if (task == null) {
            throw new IllegalArgumentException("Task can`t be null");
        }
        var checkToDo = toDoService
                .getAll()
                .stream()
                .filter(toDo -> toDo.equals(todo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ToDo not found"));
        checkToDo.getTaskList().add(task);
        return task;
    }

    @Override
    public Task updateTask(Task task) {
        return getAll().stream()
                .filter(t -> t.equals(task))
                .findFirst()
                .map(t -> {
                    t.setName(task.getName());
                    t.setPriority(task.getPriority());
                    return t;
                })
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    @Override
    public void deleteTask(Task task) {
        if (!getAll().contains(task)) {
            throw new IllegalArgumentException("Task not found");
        } else {
            getAll().remove(task);
        }
    }

    @Override
    public List<Task> getAll() {
        return toDoService.getAll()
                .stream()
                .flatMap(toDo -> toDo.getTaskList().stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getByToDo(ToDo todo) {
        if (todo == null) {
            throw new IllegalArgumentException("ToDo can`t be null");
        }
        var checkTodo = toDoService.getAll()
                .stream()
                .filter(toDo -> toDo.equals(todo))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("ToDo not found"));
        return checkTodo.getTaskList();
    }

    @Override
    public Task getByToDoName(ToDo todo, String name) {
        return getByToDo(todo).stream()
                .filter(task -> task.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

    }

    @Override
    public Task getByUserName(User user, String name) {
        return toDoService.getByUser(user).stream()
                .flatMap(toDo -> toDo.getTaskList().stream())
                .filter(task -> task.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

}
