package com.gobi.Todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gobi.Todo.repository.TodoRepository;
import com.gobi.Todo.model.TodoModel;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<TodoModel> getTodosByUser(String userId) {
        return todoRepository.findByUserId(userId);
    }

    public TodoModel createTodo(TodoModel todo, String userId) {
        todo.setUserId(userId);
        return todoRepository.save(todo);
    }

    
    public Optional<TodoModel> getTodoById(String id, String userId) {
        return todoRepository.findByIdAndUserId(id, userId);
    }
    public TodoModel updateTodo(String id, TodoModel newTodo, String userId) {
        TodoModel todo = todoRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setTitle(newTodo.getTitle());
        todo.setDescription(newTodo.getDescription());
        todo.setStatus(newTodo.getStatus());
        return todoRepository.save(todo);
    }

    public void deleteTodo(String id, String userId) {
        TodoModel todo = todoRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todoRepository.delete(todo);
    }
}