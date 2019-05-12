package com.backend.todolist.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.todolist.dao.UserDao;
import com.backend.todolist.model.user;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserDao userDao;
	
	
	@PostMapping("/register")
	public user register(@Valid @RequestBody user usr) {
		return userDao.register(usr);
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> findUser(){
		
		List<user> usr=userDao.findAll();
		
		if(usr==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(usr);
		
	}
	
	@GetMapping("/{email}/find/{password}")
	public ResponseEntity<user> userLogin(@PathVariable(value="email") String email,@PathVariable(value="password") String password){
		user usr=userDao.userLogin(email,password);
		if(usr==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(usr);
		
	}
	
}
