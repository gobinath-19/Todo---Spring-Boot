package com.example.Todo.Service;

import com.example.Todo.DAL.DBRepo;
import com.example.Todo.Model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final DBRepo dbrepo;

    @Autowired
    public TodoService(@Qualifier("todorepository") DBRepo dbrepo) {
        this.dbrepo = dbrepo;
    }

    public List<Todo> getAllTodo(){
        return dbrepo.getTodo();
    }

    public Todo createTodo(Todo newtodo){
        return dbrepo.createTodo(newtodo);
    }

    public Todo updateTodo(String id, Todo newtodo) {
        return dbrepo.updateTodo(id, newtodo);
    }

    public void deleteTodo(String id){
        dbrepo.deleteById(id);
    }

}
