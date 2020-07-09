package com.yanik.todolist.dto;

import java.util.List;

import com.yanik.todolist.model.Todo;

public class TodoDto {

	List<Todo> content;
	int total;
	public List<Todo> getContent() {
		return content;
	}
	public void setContent(List<Todo> content) {
		this.content = content;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
}
