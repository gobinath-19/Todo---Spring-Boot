package com.example.Todo.config;


import com.example.Todo.DAL.DBRepo;
import com.example.Todo.DAL.InMemory;
import com.example.Todo.DAL.MongoTodoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TodoConfig {

    @Value("${db.type}")
    private String dbType;

    private final MongoTodoRep mongoRep;
    private final InMemory inmemory;

    @Autowired
    public TodoConfig(MongoTodoRep mongoRep, InMemory inmemory) {
        this.mongoRep = mongoRep;
        this.inmemory = inmemory;
    }

    @Bean(name = "todorepository")
    public DBRepo todoRep() {
        if ("inMemory".equalsIgnoreCase(dbType)) {
            return inmemory;  // return InMemory implementation if db.type is inMemory
        } else {
            return mongoRep;  // return MongoDB implementation otherwise
        }
    }
}
