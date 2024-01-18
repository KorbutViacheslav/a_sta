package com.taskmanager.model;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"email"})
@ToString
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<ToDo> toDoList;
}
