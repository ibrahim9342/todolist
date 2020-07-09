package com.yanik.todolist.service;


import java.util.List;

import org.springframework.data.domain.Pageable;

import com.yanik.todolist.dto.TodoDto;
import com.yanik.todolist.model.Todo;

public interface TodoService {

	Todo saveOrUpdateTodo(Todo todo);
	
	void delete(Integer id);
		
	List<Todo> getTodosByUser(Long id);
	
	TodoDto getTodosByUser(Long id, Pageable pageable);
	
	Todo getTodo(Integer id);
	
	//List<Deneme> getTodosByUserDeneme(Long id);

}

