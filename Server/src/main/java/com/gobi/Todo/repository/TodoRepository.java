package com.gobi.Todo.repository;

import com.gobi.Todo.model.TodoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface TodoRepository extends MongoRepository<TodoModel, String> {
    List<TodoModel> findByUserId(String userId);
    Optional<TodoModel> findByIdAndUserId(String id, String userId); 
}
