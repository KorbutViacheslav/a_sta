package com.taskmanager.service;

import com.taskmanager.model.Role;
import com.taskmanager.model.ToDo;
import com.taskmanager.model.User;
import com.taskmanager.repository.ToDoRepository;
import com.taskmanager.service.impl.ToDoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {
    @Mock
    ToDoRepository toDoRepository;

    @InjectMocks
    ToDoServiceImpl toDoService;

    @Test
    void create() {
        ToDo toDo = new ToDo(1L, "Todo 1", LocalDateTime.now(), new User());

        when(toDoRepository.save(toDo)).thenReturn(toDo);

        ToDo result = toDoService.create(toDo);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Todo 1");

        verify(toDoRepository, times(1)).save(toDo);
    }

    @Test
    void readById() {
        long toDoId = 1L;
        ToDo toDo = new ToDo(toDoId, "Todo 1", LocalDateTime.now(), new User());

        when(toDoRepository.findById(toDoId)).thenReturn(Optional.of(toDo));

        ToDo result = toDoService.readById(toDoId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(toDoId);
        assertThat(result.getTitle()).isEqualTo("Todo 1");

        verify(toDoRepository, times(1)).findById(toDoId);
    }

    @Test
    void update() {
        ToDo toDo = new ToDo(1L, "Updated Todo", LocalDateTime.now(), new User());

        when(toDoRepository.save(toDo)).thenReturn(toDo);

        ToDo result = toDoService.update(toDo);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Updated Todo");

        verify(toDoRepository, times(1)).save(toDo);
    }

    @Test
    void delete() {
        long toDoId = 1L;
        ToDo toDo = new ToDo(toDoId, "Sample Todo", LocalDateTime.now(), new User());

        ToDoRepository mockedRepository = Mockito.mock(ToDoRepository.class);

        List<ToDo> todosBeforeDeletion = Arrays.asList(toDo);
        doNothing().when(mockedRepository).deleteById(toDoId);
        ToDoService toDoService = new ToDoServiceImpl(mockedRepository);
        toDoService.delete(toDoId);

        verify(mockedRepository, times(1)).deleteById(toDoId);

        List<ToDo> todosAfterDeletion = Collections.emptyList();
        assertThat(todosAfterDeletion.size()).isEqualTo(todosBeforeDeletion.size() - 1);
    }

    @Test
    void getAll() {
        List<ToDo> allTodos = Arrays.asList(
                new ToDo(1L, "Todo 1", LocalDateTime.now(), new User()),
                new ToDo(2L, "Todo 2", LocalDateTime.now(), new User()),
                new ToDo(3L, "Todo 3", LocalDateTime.now(), new User())
        );

        when(toDoRepository.findAll()).thenReturn(allTodos);

        List<ToDo> result = toDoService.getAll();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(3);

        verify(toDoRepository, times(1)).findAll();
    }

    @Test
    void getByUserId() {
        long userId = 1L;
        Role role = new Role();

        User user1 = new User(1L, "Jon", "Jonson", "jonson@gmail.com", "Aa23123!@#", role, null, null);
        User user2 = new User(2L, "Jon2", "Jonson2", "jonson2@gmail.com", "Aa23123!@#", role, null, null);
        List<ToDo> todosByUserId = Arrays.asList(
                new ToDo(1L, "Todo 1", LocalDateTime.now(), user1),
                new ToDo(2L, "Todo 2", LocalDateTime.now(), user2)
        );

        when(toDoRepository.findAllByOwnerId(userId)).thenReturn(todosByUserId);

        List<ToDo> result = toDoService.getByUserId(userId);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);

        verify(toDoRepository, times(1)).findAllByOwnerId(userId);
    }
}