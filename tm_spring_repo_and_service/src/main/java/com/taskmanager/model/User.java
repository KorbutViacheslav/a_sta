package com.taskmanager.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.StringJoiner;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name", nullable = false)
    @Pattern(regexp = "^[A-Z][a-zA-Z]+([-][A-Z][a-zA-Z]+)?$", message = "Name must be valid")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Pattern(regexp = "^[A-Z][a-zA-Z]+([-][A-Z][a-zA-Z]+)?$", message = "Surname must be valid")
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email(message = "Email must be valid")
    @NotBlank
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password can't be blank")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must meet complexity requirements")
    private String password;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<ToDo> toDos;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "todo_collaborator",
            joinColumns = @JoinColumn(name = "collaborator_id", referencedColumnName = "id"),
            inverseJoinColumns=@JoinColumn(name = "todo_id", referencedColumnName = "id"))
    private List<ToDo> toDoList;

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("email='" + email + "'")
                .toString();
    }
}

