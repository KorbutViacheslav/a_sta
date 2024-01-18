package com.taskmanager.model;


import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Task {
    private String name;
    private Priority priority;
}
