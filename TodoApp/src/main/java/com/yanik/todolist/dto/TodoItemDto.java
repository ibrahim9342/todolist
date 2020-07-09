package com.yanik.todolist.dto;

import java.util.List;
import com.yanik.todolist.model.TodoItem;

public class TodoItemDto {

	List<TodoItem> content;
	int total;
	public List<TodoItem> getContent() {
		return content;
	}
	public void setContent(List<TodoItem> content) {
		this.content = content;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
}
