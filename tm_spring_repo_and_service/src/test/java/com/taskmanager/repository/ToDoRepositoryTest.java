package com.taskmanager.repository;

import com.taskmanager.model.Role;
import com.taskmanager.model.ToDo;
import com.taskmanager.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class ToDoRepositoryTest {


    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ToDoRepository toDoRepository;

    @Test
    void testFindAllByOwnerId() {
        Role userRole = new Role();
        userRole.setName("USER");
        testEntityManager.persistAndFlush(userRole);

        User user = new User();
        user.setFirstName("Jon");
        user.setLastName("Jonson");
        user.setEmail("jonson@gmail.com");
        user.setPassword("Aa23123!@#");
        user.setRole(userRole);
        testEntityManager.persistAndFlush(user);
        System.out.println(user);

        ToDo toDo1 = new ToDo();
        toDo1.setTitle("Task 1");
        toDo1.setOwner(user);
        toDo1.setCreatedAt(LocalDateTime.now());
        testEntityManager.persistAndFlush(toDo1);

        ToDo toDo2 = new ToDo();
        toDo2.setTitle("Task 2");
        toDo2.setOwner(user);
        toDo2.setCreatedAt(LocalDateTime.now());
        testEntityManager.persistAndFlush(toDo2);

        List<ToDo> foundToDos = toDoRepository.findAllByOwnerId(user.getId());

        assertThat(foundToDos).isNotEmpty();
        assertThat(foundToDos).hasSize(2);
        assertThat(foundToDos.get(0).getTitle()).isEqualTo("Task 1");
        assertThat(foundToDos.get(1).getTitle()).isEqualTo("Task 2");
    }

}