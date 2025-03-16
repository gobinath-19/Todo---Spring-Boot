package com.example.Todo.exception;

public class TodoNotFound extends RuntimeException {
    public TodoNotFound(String message) {
        super(message);
    }
}
