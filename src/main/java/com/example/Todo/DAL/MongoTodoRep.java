package com.example.Todo.DAL;

import com.example.Todo.Model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoTodoRep implements DBRepo {

    @Autowired
    private MongoRep mongorep;

    @Override
    public List<Todo> getTodo() {
        return mongorep.findAll();
    }

    @Override
    public Todo createTodo(Todo todo) {
        return mongorep.save(todo);  // Save the new todo and return it
    }

    @Override
    public Todo updateTodo(String id, Todo newtodo) {
        if (!mongorep.existsById(id)) {
            throw new RuntimeException("Todo Not Found");
        }
        newtodo.setId(id);
        return mongorep.save(newtodo);
    }

    @Override
    public void deleteById(String id) {
        if (!mongorep.existsById(id)) {
            throw new RuntimeException("Give Id to delete");
        }
        mongorep.deleteById(id);
    }

    @Override
    public Optional<Todo> getTodoById(String id) {
        return mongorep.findById(id);
    }
}