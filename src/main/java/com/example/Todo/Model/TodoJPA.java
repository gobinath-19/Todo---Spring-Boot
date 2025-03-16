package com.example.Todo.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "todo")  // Ensure this matches the PostgreSQL table name
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoJPA {
    
    @Id
    private String id;

    @Column(nullable = false)  // Ensures title cannot be null
    private String title;

    @Column(nullable = true)
    private String description;
}
