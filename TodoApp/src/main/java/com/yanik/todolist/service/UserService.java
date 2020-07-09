package com.yanik.todolist.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.yanik.todolist.model.User;

public interface UserService {

	Optional<User> findUserName(String username);
	
	ResponseEntity<?> saveUser(User user);
	
}
