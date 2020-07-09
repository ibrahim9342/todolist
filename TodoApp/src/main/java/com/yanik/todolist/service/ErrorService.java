package com.yanik.todolist.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ErrorService {
	ResponseEntity<?> validationService(BindingResult result);
}
