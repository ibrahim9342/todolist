package com.backend.todolist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.todolist.model.user;
import com.backend.todolist.repository.UserRepository;;

@Service
public class UserDao {
	
	@Autowired
	UserRepository userRepository;
		
	

	public user register(user usr) {
		return userRepository.save(usr);
	}
	
	public List<user> findAll() {
		return userRepository.findAll();
	}
	
	public user userLogin(String email,String password) {
		return userRepository.findUser(email, password);
	}
	
}
