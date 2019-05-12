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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.todolist.dao.ToDoItemDao;
import com.backend.todolist.model.ToDoItem;
import com.backend.todolist.model.ToDoList;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/todoitem")
public class ToDoItemController {

	@Autowired
	ToDoItemDao toDoItemDao;
	
	@PostMapping("/save")
	public ToDoItem save(@Valid @RequestBody ToDoItem todolist) {
		return toDoItemDao.save(todolist);
	}
	
	
	@GetMapping("/ordertype/{listid}/{ordertype}")
	public ResponseEntity<List<ToDoItem>> ordercreatedAt(@PathVariable(value="listid") Long listid,
			@PathVariable(value="ordertype") String ordertype){
		List<ToDoItem> todoitems=toDoItemDao.findAllItemsOrder(listid,ordertype);
		if(todoitems==null) { 
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(todoitems);
	}
	
	@GetMapping("/{listid}/{statu}/{expired}/{searchname}")
	public ResponseEntity<List<ToDoItem>> listfilter(@PathVariable(value="listid") Long listid,
			@PathVariable(value="statu") String statu,
			@PathVariable(value="expired") String expired,
			@PathVariable(value="searchname") String searchname){
		
		List<ToDoItem> todoitems=toDoItemDao.findAllItemss(listid,removeFirst(statu),removeFirst(expired),removeFirst(searchname));
		if(todoitems==null) { 
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(todoitems);
	}
	
	
	public String removeFirst(String input)
	{
	    return input.substring(1);
	}
	
	@GetMapping("/{listid}")
	public ResponseEntity<List<ToDoItem>> list(@PathVariable(value="listid") Long listid){
		List<ToDoItem> todoitems=toDoItemDao.findAllItems(listid);
		if(todoitems==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(todoitems);
	}
	
	
	@PutMapping("/complate/{id}/{statu}")
	public ResponseEntity<ToDoItem> markedcomplate(@PathVariable(value="id") Long id,@PathVariable(value="statu") String statu){
		
		ToDoItem item=toDoItemDao.findOne(id);
		if(item==null) {
			return ResponseEntity.notFound().build();
		}
		item.setStatu(statu);
		ToDoItem updateItems=toDoItemDao.save(item);
		return ResponseEntity.ok().body(updateItems);
		
		
		
	}
	
	@DeleteMapping("/delete/{recid}")
	public ResponseEntity<ToDoList> delete(@PathVariable(value="recid") Long recid){
		
		ToDoItem todolist=toDoItemDao.findOne(recid);
		if(todolist==null) {
			return ResponseEntity.notFound().build();
		}
		toDoItemDao.delete(todolist);
		
		return ResponseEntity.ok().build();
	}
	
	
}
