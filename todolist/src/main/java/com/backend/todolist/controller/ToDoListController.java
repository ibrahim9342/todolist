package com.backend.todolist.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.todolist.dao.ToDoListDao;
import com.backend.todolist.model.ToDoList;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/todolist")
public class ToDoListController {

	@Autowired
	ToDoListDao toDoListDao;
	
	@PostMapping("/save")
	public ToDoList save(@Valid @RequestBody ToDoList todolist) {
		return toDoListDao.save(todolist);
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<List<ToDoList>> list(@PathVariable(value="userid") String userid){
		List<ToDoList> todolist=toDoListDao.findAllToDo(userid);
		if(todolist==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(todolist);
	}
	
	@DeleteMapping("/delete/{recid}")
	public ResponseEntity<ToDoList> delete(@PathVariable(value="recid") Long recid){
		
		ToDoList todolist=toDoListDao.findOne(recid);
		if(todolist==null) {
			return ResponseEntity.notFound().build();
		}
		toDoListDao.delete(todolist);
		
		return ResponseEntity.ok().build();
		
		
	}
	
	
}
