package com.gobi.Todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gobi.Todo.model.UserModel;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String>{
    Optional<UserModel> findByUsername(String username);
}
