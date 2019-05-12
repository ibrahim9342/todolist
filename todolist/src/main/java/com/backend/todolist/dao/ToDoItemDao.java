package com.backend.todolist.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.todolist.model.ToDoItem;
import com.backend.todolist.repository.ToDoItemRepository;

@Service
public class ToDoItemDao {

	@Autowired
	ToDoItemRepository toDoItemRepository;
	
	public ToDoItem save(ToDoItem todolist) {
		return toDoItemRepository.save(todolist);
	}
	
	public List<ToDoItem> findAllItems(Long listid) {
		return toDoItemRepository.findAllItems(listid);
	}
	
	public List<ToDoItem> findAllItemss(Long listid,String statu,String expired,String name) {
		if(!expired.isEmpty()) {
			return toDoItemRepository.findAllItemsExp(listid,statu,name,new Date());
		}else {
			return toDoItemRepository.findAllItemss(listid,statu,name); 
		}
	}
	
	public List<ToDoItem> findAllItemsOrder(Long listid,String ordertype) {
		if(ordertype.equals("ASC")) {
			return toDoItemRepository.findAllItemsOrderAsc(listid);
		}else {
			return toDoItemRepository.findAllItemOrderDesc(listid); 
		}
	}
	
	public ToDoItem findOne(Long recid) {
		return toDoItemRepository.findOne(recid);
	}
	
	public void delete(ToDoItem todoItem) { 
		toDoItemRepository.delete(todoItem);
	}
	
}
