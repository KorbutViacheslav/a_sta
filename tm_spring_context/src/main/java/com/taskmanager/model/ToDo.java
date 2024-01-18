package com.taskmanager.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"title", "user"})
@ToString
public class ToDo {
    private String title;
    private LocalDateTime createdAt;
    private User user;
    private List<Task> taskList;
}
