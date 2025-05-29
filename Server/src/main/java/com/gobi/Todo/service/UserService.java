package com.gobi.Todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gobi.Todo.model.UserModel;
import com.gobi.Todo.repository.UserRepository;

import org.springframework.dao.DuplicateKeyException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userrepository;

    public UserModel addUser(UserModel user) {
        try {
            return userrepository.save(user);
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("Username already exists");
        }
    }

    public List<UserModel> getUser() {
        return userrepository.findAll();
    }

    public UserModel updateTodo(String id, UserModel user) {
        UserModel existingUser = userrepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            return userrepository.save(existingUser);
        } else {
            return null;
        }
    }

    public String deleteUser(String id) {
        userrepository.deleteById(id);
        return "User deleted successfully";
    }
}
