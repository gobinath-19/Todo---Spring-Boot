package com.example.Todo.DAL;

import com.example.Todo.Model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoRep extends MongoRepository<Todo, String> {
}