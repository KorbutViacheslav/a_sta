package com.taskmanager.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    private Long id;

    @NotBlank(message = "Name can't  be blank")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Priority can't  be null")
    private Priority priority;

    @ManyToOne(optional = false)
    @JoinColumn(name = "state_id", referencedColumnName="id")
    private State state;

    @ManyToOne(optional = false)
    @JoinColumn(name = "todo_id", referencedColumnName="id")
    private ToDo toDo;
}
