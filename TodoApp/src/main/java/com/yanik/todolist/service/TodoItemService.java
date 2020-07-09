package com.yanik.todolist.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.yanik.todolist.dto.TodoItemDto;
import com.yanik.todolist.model.TodoItem;

public interface TodoItemService {

	TodoItem saveOrUpdateItem(TodoItem todoItem);
	
	void delete(Integer id);
	
	TodoItemDto getUserItems(long userId, Pageable pageable);
	
	TodoItem getTodoItem(Integer todoItemId);
	
	//List<TodoItem> getUserItems(long userId, Pageable pageable);
	
	List<TodoItem> getTodoItems(int todoId,long userId);
	
}
