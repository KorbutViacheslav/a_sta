package com.taskmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todos")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Title can't be blank")
    private String title;

    @Column(name = "created_at", nullable = false)
    @NotNull(message = "Date can't be null")
    @CreationTimestamp
    private LocalDateTime createdAt;


    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", referencedColumnName="id")
    private User owner;


}
