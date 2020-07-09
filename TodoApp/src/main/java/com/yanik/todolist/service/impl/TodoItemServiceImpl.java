package com.yanik.todolist.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yanik.todolist.dao.TodoItemRepository;
import com.yanik.todolist.dto.TodoDto;
import com.yanik.todolist.dto.TodoItemDto;
import com.yanik.todolist.model.Todo;
import com.yanik.todolist.model.TodoItem;
import com.yanik.todolist.model.User;
import com.yanik.todolist.service.TodoItemService;

@Service
@Transactional
public class TodoItemServiceImpl implements TodoItemService {

	@Autowired
	private TodoItemRepository todoItemRepository;
	
	@Override
	public TodoItemDto getUserItems(long userId, Pageable pageable){
		
		Todo todo = new Todo();
		todo.setId(0);
		todo.setUser(new User(userId));
		//Page<TodoItem> itemPage = todoItemRepository.findByTodo(todo, pageable);
		
		Page<TodoItem> itemPage = todoItemRepository.findUserItems(userId, pageable);
		TodoItemDto dto = new TodoItemDto();
		dto.setContent(itemPage.getContent());
		dto.setTotal((int)itemPage.getTotalElements());
		return dto;
	}
	
	@Override
	public TodoItem getTodoItem(Integer todoItemId) {
		return todoItemRepository.getOne(todoItemId);
	}
	
	
	@Override
	public List<TodoItem> getTodoItems(int todoId,long userId){
		return todoItemRepository.findTodoItems(todoId, userId);
	}
	
	@Override
	public TodoItem saveOrUpdateItem(TodoItem todoItem) {
		return todoItemRepository.save(todoItem);
	}

	@Override
	public void delete(Integer id) {
		todoItemRepository.deleteById(id);
	}

}
