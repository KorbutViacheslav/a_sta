package com.taskmanager.service.impl;

import com.taskmanager.model.ToDo;
import com.taskmanager.repository.ToDoRepository;
import com.taskmanager.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {
    private final ToDoRepository toDoRepository;

    @Override
    public ToDo create(ToDo todo) {
        return toDoRepository.save(todo);
    }

    @Override
    public ToDo readById(long id) {
        return toDoRepository.findById(id).orElse(null);
    }

    @Override
    public ToDo update(ToDo todo) {
        return toDoRepository.save(todo);
    }

    @Override
    public void delete(long id) {
        toDoRepository.deleteById(id);
    }

    @Override
    public List<ToDo> getAll() {
        return toDoRepository.findAll();
    }

    @Override
    public List<ToDo> getByUserId(long userId) {
        return toDoRepository.findAllByOwnerId(userId);
    }
}
