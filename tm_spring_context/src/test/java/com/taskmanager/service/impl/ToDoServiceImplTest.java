package com.taskmanager.service.impl;

import com.taskmanager.config.ConfigApp;
import com.taskmanager.model.ToDo;
import com.taskmanager.model.User;
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

class ToDoServiceImplTest {
    private static ToDoService toDoService;
    private static UserService userService;

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        var context = new AnnotationConfigApplicationContext(ConfigApp.class);
        toDoService = context.getBean(ToDoService.class);
        userService = context.getBean(UserService.class);
        context.close();
    }

    @AfterEach
    public void clearService() {
        userService.getAll().clear();
    }

    @Test
    void addTodo() {
        User user = new User("Jack", "Smith", "jack.smith@gmail.com", "password", new ArrayList<>());
        userService.addUser(user);
        ToDo todo = new ToDo("TODO 1", LocalDateTime.now(), null, new ArrayList<>());

        ToDo addedTodo = toDoService.addTodo(todo, user);

        assertNotNull(addedTodo);
        assertEquals(todo, addedTodo);
        assertTrue(user.getToDoList().contains(addedTodo));
    }

    @Test
    void updateTodo() {
        User user = new User("Jack", "Smith", "jack.smith@gmail.com", "password", new ArrayList<>());
        userService.addUser(user);

        ToDo todo = new ToDo("TODO 1", LocalDateTime.now(), null, new ArrayList<>());
        toDoService.addTodo(todo, user);

        assertThrows(IllegalArgumentException.class, () -> toDoService.updateTodo(null));

        ToDo updatedTodDo = new ToDo("TODO 1", LocalDateTime.now(), null, new ArrayList<>());
        updatedTodDo.setUser(user);
        updatedTodDo.setCreatedAt(LocalDateTime.now().minusDays(1));

        assertEquals(todo,toDoService.updateTodo(updatedTodDo));
    }

    @Test
    void deleteTodo() {
        User user = new User("Jack", "Smith", "jack.smith@gmail.com", "password", new ArrayList<>());
        userService.addUser(user);
        ToDo todo = new ToDo("TODO 1", LocalDateTime.now(), null, new ArrayList<>());
        toDoService.addTodo(todo, user);

        toDoService.deleteTodo(todo);

        assertTrue(user.getToDoList().isEmpty());
    }

    @Test
    void getAll() {
        User user1 = new User("Jack", "Smith", "jack.smith@gmail.com", "password", new ArrayList<>());
        userService.addUser(user1);

        User user2 = new User("Jack", "Smith", "jack.smith@gmail.com", "password", new ArrayList<>());
        user2.setEmail("tom.ros@gmail.com");
        userService.addUser(user2);

        ToDo todo1 = new ToDo("TODO 1", LocalDateTime.now(), null, new ArrayList<>());
        ToDo todo2 = new ToDo("TODO 2", LocalDateTime.now(), null, new ArrayList<>());

        toDoService.addTodo(todo1, user1);
        toDoService.addTodo(todo2, user2);

        List<ToDo> todos = toDoService.getAll();

        assertEquals(2, todos.size());
        assertTrue(todos.contains(todo1));
        assertTrue(todos.contains(todo2));
    }

    @Test
    void getByUser() {
        User user = new User("Jack", "Smith", "jack.smith@gmail.com", "password", new ArrayList<>());
        ToDo todo1 = new ToDo("TODO 1", LocalDateTime.now(), null, new ArrayList<>());
        ToDo todo2 = new ToDo("TODO 2", LocalDateTime.now(), null, new ArrayList<>());

        toDoService.addTodo(todo1, user);
        toDoService.addTodo(todo2, user);

        List<ToDo> userTodos = toDoService.getByUser(user);

        assertEquals(2, userTodos.size());
        assertTrue(userTodos.contains(todo1));
        assertTrue(userTodos.contains(todo2));
    }

    @Test
    void getByUserTitle() {
        User user = new User("Jack", "Smith", "jack.smith@gmail.com", "password", new ArrayList<>());
        ToDo todo1 = new ToDo("TODO 1", LocalDateTime.now(), null, new ArrayList<>());
        ToDo todo2 = new ToDo("TODO 2", LocalDateTime.now(), null, new ArrayList<>());

        toDoService.addTodo(todo1, user);
        toDoService.addTodo(todo2, user);

        ToDo todo = toDoService.getByUserTitle(user, "TODO 1");

        assertNotNull(todo);
        assertEquals(todo1, todo);
    }
}