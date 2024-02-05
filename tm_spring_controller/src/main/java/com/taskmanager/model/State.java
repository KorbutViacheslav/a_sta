package com.taskmanager.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "states")
public class State {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    private Long id;

    @NotNull(message = "Name may not be null")
    @Pattern(regexp = "^[A-Za-z]+(\\\\s+[A-Za-z]+)*$", message = "Surname must be valid")
    private String name;

    @OneToMany(mappedBy = "state")
    private List<Task> tasks;

}
