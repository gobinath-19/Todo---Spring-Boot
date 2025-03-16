package com.example.Todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.Todo")
@EnableJpaRepositories(basePackages = "com.example.Todo.DAL")
@EnableMongoRepositories(basePackages = "com.example.Todo.DAL")
public class TodoApplication {

    @Value("${db.type}")
    private String dbType;

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Bean
    CommandLineRunner checkConfig() {
        return args -> {
            System.out.println("Database Type: " + dbType);
        };
    }
}