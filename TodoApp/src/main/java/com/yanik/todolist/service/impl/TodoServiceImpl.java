package com.yanik.todolist.service.impl;


import java.util.List;

import javax.transaction.TransactionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yanik.todolist.dao.TodoRepository;
import com.yanik.todolist.dto.TodoDto;
import com.yanik.todolist.model.Todo;
import com.yanik.todolist.model.User;
import com.yanik.todolist.service.TodoService;


@Service
@TransactionScoped
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	
	@Override
	public List<Todo> getTodosByUser(Long id){
		return todoRepository.findByUser(new User(id));
	}
	
	
	@Override
	public TodoDto getTodosByUser(Long id, Pageable pageable){
		
		//Pageable pageable = PageRequest.of(0, 10);
		Page<Todo> todoPage = todoRepository.findByUser(new User(id),pageable);
		TodoDto dto = new TodoDto();
		dto.setContent(todoPage.getContent());
		dto.setTotal((int)todoPage.getTotalElements());
		
		return dto;
	}
	
	@Override
	public Todo saveOrUpdateTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	@Override
	public void delete(Integer id) {
		todoRepository.deleteById(id);
	}

	@Override
	public Todo getTodo(Integer id) {
		return todoRepository.getOne(id);
	}

	
//	@Override
//	public List<Deneme> getTodosByUserDeneme(Long id){
//		return todoRepository.getxxDeneme(new User(id));
//	}
	
	
	
}
