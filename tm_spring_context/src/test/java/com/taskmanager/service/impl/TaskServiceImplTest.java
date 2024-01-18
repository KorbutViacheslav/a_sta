package com.taskmanager.service.impl;

import com.taskmanager.config.ConfigApp;
import com.taskmanager.model.Priority;
import com.taskmanager.model.Task;
import com.taskmanager.model.ToDo;
import com.taskmanager.model.User;
import com.taskmanager.service.TaskService;
import com.taskmanager.service.ToDoService;
import com.taskmanager.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceImplTest {
    private static UserService userService;
    private static ToDoService toDoService;
    private static TaskService taskService;

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        var context = new AnnotationConfigApplicationContext(ConfigApp.class);
        userService = context.getBean(UserService.class);
        toDoService = context.getBean(ToDoService.class);
        taskService = context.getBean(TaskService.class);
        context.close();
    }

    @AfterEach
    public void clearService() {
        userService.getAll().clear();
    }

    // TODO: implement test
}