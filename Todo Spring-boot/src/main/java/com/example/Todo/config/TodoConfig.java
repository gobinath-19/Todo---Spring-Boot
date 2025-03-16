package com.example.Todo.config;

import com.example.Todo.DAL.DBRepo;
import com.example.Todo.DAL.TodoHibernateRepo;
import com.example.Todo.DAL.HibernateRepo;
import com.example.Todo.DAL.InMemory;
import com.example.Todo.DAL.MongoTodoRep;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final TodoHibernateRepo hibernate;

    @Autowired
    public TodoConfig(MongoTodoRep mongoRep, InMemory inmemory, TodoHibernateRepo hibernate) {
        this.mongoRep = mongoRep;
        this.inmemory = inmemory;
        this.hibernate = hibernate;
    }

    @Scope("prototype")
    @Bean(name = "todorepository")
    public DBRepo todoRep() {
        if ("mongoRep".equalsIgnoreCase(dbType)) {
            return mongoRep;  // Use MongoDB repository
        } else if ("inMemory".equalsIgnoreCase(dbType)) {
            return inmemory;  // Use in-memory database
        }
        return null;
    }

    @Scope("prototype")
    @Bean(name = "todorepositoryHibernate")
    public HibernateRepo todoH() {
        if ("hibernate".equalsIgnoreCase(dbType)) {
            return hibernate;
        }
        return null;
    }
}