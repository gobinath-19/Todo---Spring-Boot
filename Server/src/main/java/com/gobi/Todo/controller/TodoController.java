package com.gobi.Todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.gobi.Todo.jwt.JwtUtil;
import com.gobi.Todo.model.TodoModel;
import com.gobi.Todo.service.TodoService;


@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/test")
    public String test() {
        return "Hello World!";
    }

    private String extractToken(String header) {
        return header.substring(7); // "Bearer <token>"
    }


@GetMapping
    public ResponseEntity<List<TodoModel>> getTodos(@RequestHeader("Authorization") String authHeader) {
        String userId = jwtUtil.extractUsername(extractToken(authHeader));
        return ResponseEntity.ok(todoService.getTodosByUser(userId));
    }

    @PostMapping
    public ResponseEntity<TodoModel> createTodo(@RequestHeader("Authorization") String authHeader,
                                           @RequestBody TodoModel todo) {
        String userId = jwtUtil.extractUsername(extractToken(authHeader));
        return ResponseEntity.ok(todoService.createTodo(todo, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoModel> getTodoById(@PathVariable String id,
                                            @RequestHeader("Authorization") String authHeader) {
        String userId = jwtUtil.extractUsername(extractToken(authHeader));
        return todoService.getTodoById(id, userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoModel> updateTodo(@PathVariable String id,
                                           @RequestHeader("Authorization") String authHeader,
                                           @RequestBody TodoModel todo) {
        String userId = jwtUtil.extractUsername(extractToken(authHeader));
        return ResponseEntity.ok(todoService.updateTodo(id, todo, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String id,
                                           @RequestHeader("Authorization") String authHeader) {
        String userId = jwtUtil.extractUsername(extractToken(authHeader));
        todoService.deleteTodo(id, userId);
        return ResponseEntity.noContent().build();
    }
}