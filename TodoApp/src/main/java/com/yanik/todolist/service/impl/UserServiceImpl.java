package com.yanik.todolist.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yanik.todolist.dao.UserRepository;
import com.yanik.todolist.model.ERole;
import com.yanik.todolist.model.Role;
import com.yanik.todolist.model.User;
import com.yanik.todolist.service.RoleService;
import com.yanik.todolist.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public Optional<User> findUserName(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public ResponseEntity<?> saveUser(User user) {
		
		if (userRepository.existsByUsername(user.getUsername())) {
			return new ResponseEntity<String>("Error: Username is already taken!", HttpStatus.BAD_REQUEST);			
		}

		if (userRepository.existsByEmail(user.getEmail())) {
			return new ResponseEntity<String>("Error: Email is already in use!", HttpStatus.BAD_REQUEST);		
		}
		
		user.setPassword(encoder.encode(user.getPassword()));
		Set<Role> roles = new HashSet<>();

		Role userRole = roleService.findRoleByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);

		user.setRoles(roles);
		 
		return  new ResponseEntity<User>(userRepository.save(user), HttpStatus.CREATED);
	}

}
