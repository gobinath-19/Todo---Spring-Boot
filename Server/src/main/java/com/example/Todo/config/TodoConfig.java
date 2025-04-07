package com.example.Todo.config;

import com.example.Todo.DAL.DBRepo;
import com.example.Todo.DAL.InMemory;
import com.example.Todo.DAL.MongoTodoRep;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TodoConfig {

    @Value("${db.type}")
    private String dbType;

    private final MongoTodoRep mongoRep;
    private final InMemory inmemory;


    public TodoConfig(MongoTodoRep mongoRep, InMemory inmemory) {
        this.mongoRep = mongoRep;
        this.inmemory = inmemory;
       
    }

    @Scope("prototype")
    @Bean(name = "todorepository")
    public DBRepo todoRep() {
        if ("mongoRep".equalsIgnoreCase(dbType)) {
            return mongoRep;  // Use MongoDB repository
        } else if ("inMemory".equalsIgnoreCase(dbType)) {
            return inmemory;  // Use in-memory database
        } else{
        throw new IllegalArgumentException("Invalid db.type: " + dbType);
        }
    }   
}