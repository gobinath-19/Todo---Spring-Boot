package com.example.Todo.DAL;


import com.example.Todo.Model.TodoJPA;

import java.util.List;
import java.util.Optional;

public interface HibernateRepo{
    

    List<TodoJPA> findAll();
    Optional<TodoJPA> findById(Long id);
    TodoJPA save(TodoJPA todo);
    TodoJPA update(TodoJPA todo);
    void deleteById(Long id);

}
