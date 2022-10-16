package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Author;

public interface UserService<T> {

    T addUser(String name, String username, String email, String password);

    int deleteUser(String id);

    Optional<T> getUser(String id);

    String getUserIdByEmail(String email);
    
    String getUserIdByUsername(String username);

    Optional<T> getUserByEmail(String email);
    
    Optional<T> getUserByUsername(String username);

    List<T> getUsers();

    boolean passwordMatch(String id, String password);

   
 

}
