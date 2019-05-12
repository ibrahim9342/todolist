package com.backend.todolist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.todolist.model.ToDoList;
import com.backend.todolist.repository.ToDoListRepository;

@Service
public class ToDoListDao {

	@Autowired
	ToDoListRepository toDoListRepository;
	
	public ToDoList save(ToDoList todolist) {
		return toDoListRepository.save(todolist);
	}
	
	public List<ToDoList> findAllToDo(String userid) {
		return toDoListRepository.findAllToDo(userid);
	}
	
	public ToDoList findOne(Long recid) {
		return toDoListRepository.findOne(recid);
	}
	
	public void delete(ToDoList todolist) {
		toDoListRepository.delete(todolist);
	}
	
}
