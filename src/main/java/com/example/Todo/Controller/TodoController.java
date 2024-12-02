package com.example.Todo.Controller;

import com.example.Todo.Model.Todo;
import com.example.Todo.Service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping ("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodo(){
        return todoService.getAllTodo();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo newTodo){
        return todoService.createTodo(newTodo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable String id, @RequestBody Todo newtodo){
        return todoService.updateTodo(id, newtodo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id){
        todoService.deleteTodo(id);
    }


}
