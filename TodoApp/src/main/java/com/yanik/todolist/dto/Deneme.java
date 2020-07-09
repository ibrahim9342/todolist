package com.yanik.todolist.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.yanik.todolist.model.Views;

import lombok.Value;

@Value
public class Deneme {

	@JsonView(Views.UserView.class)
	private String todoName;

	public Deneme(String todoName) {
		super();
		this.todoName = todoName;
	}

	
	
	
	
}
