package com.taskmanager.service.impl;

import com.taskmanager.model.ToDo;
import com.taskmanager.model.User;
import com.taskmanager.service.ToDoService;
import com.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private final UserService userService;

    @Override
    public ToDo addTodo(ToDo todo, User user) {
        if (user == null) throw new IllegalArgumentException("User can not be null");
        if (todo == null) throw new IllegalArgumentException("ToDo can not be null");
        todo.setUser(user);
        user.getToDoList().add(todo);
        return todo;
    }

    @Override
    public ToDo updateTodo(ToDo todo) {
        if (todo == null || todo.getTitle() == null || todo.getUser() == null) {
            throw new IllegalArgumentException("Updated ToDo, title, and user can not be null");
        }
        String updatedTitle = todo.getTitle();
        User updatedUser = todo.getUser();
        ToDo updatedToDo = userService.getAll().stream()
                .flatMap(user -> user.getToDoList().stream())
                .filter(existing -> existing.getTitle().equals(updatedTitle) && existing.getUser().equals(updatedUser))
                .findFirst().orElseThrow(() -> new RuntimeException("ToDo with title and user not found"));

        updatedToDo.setTaskList(todo.getTaskList());
        updatedToDo.setCreatedAt(todo.getCreatedAt());

        return updatedToDo;
        }

    @Override
    public void deleteTodo(ToDo todo) {
        if (todo == null) throw new IllegalArgumentException("ToDo can not be null");
        userService.getAll().stream()
                .map(User::getToDoList)
                .forEach(x -> x.remove(todo));
    }

    @Override
    public List<ToDo> getAll() {
        return userService.getAll().stream()
                .flatMap(x -> x.getToDoList().stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<ToDo> getByUser(User user) {
        if (user == null) throw new IllegalArgumentException("User can not be null");
        return user.getToDoList();
    }

    @Override
    public ToDo getByUserTitle(User user, String title) {
        if (user == null) throw new IllegalArgumentException("User can not be null");
        return user.getToDoList().stream()
                .filter(toDo -> toDo.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }
}
