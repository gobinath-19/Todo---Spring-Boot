package com.gobi.Todo.model;

import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "todo")
public class TodoModel {

    @Id
    private String id;

    private String title;
    private String description;
    private Status status;
    private String userId;

    enum Status{
        COMPLETED,
        PENDING
    }
}
