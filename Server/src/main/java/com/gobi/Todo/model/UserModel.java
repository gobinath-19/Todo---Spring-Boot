package com.gobi.Todo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class UserModel {

    @Id
    private String id;

    private String email;
    private String username;
    private String password;
}
