package com.yanik.todolist.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.yanik.todolist.dto.CustomUserDetails;
import com.yanik.todolist.dto.LoginRequest;
import com.yanik.todolist.dto.LoginResponse;
import com.yanik.todolist.model.Todo;
import com.yanik.todolist.model.User;
import com.yanik.todolist.model.Views;
import com.yanik.todolist.service.ErrorService;
import com.yanik.todolist.service.UserService;
import com.yanik.todolist.utils.JwtUtils;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
    @Autowired
    private ErrorService errorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	//@JsonView(Views.SuperAdminView.class)
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody(required = false) LoginRequest loginRequest, BindingResult result) {
		
		if(loginRequest == null) {
			loginRequest = new LoginRequest();
		}
		
		ResponseEntity<?> errorMap = errorService.validationService(result);
		if(errorMap != null) 
			return errorMap;
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(), 
						loginRequest.getPassword()
				)
		);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = "Bearer " + jwtUtils.generateJwtToken(authentication);
		
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())	
				.collect(Collectors.toList());

		return ResponseEntity.ok(new LoginResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {	
		return userService.saveUser(user);
	}
	
	
	
    @GetMapping(value = "/user")
    public ResponseEntity<?> getUserTodos(Authentication authentication, @RequestHeader (name="Authorization") String jwt){
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())	
				.collect(Collectors.toList());

		return ResponseEntity.ok(new LoginResponse(jwt, 
												 	userDetails.getId(), 
												 	userDetails.getUsername(), 
												 	userDetails.getEmail(), 
												 	roles));
    }
	
}
