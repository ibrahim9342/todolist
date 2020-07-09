package com.yanik.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.yanik.todolist.dto.CustomUserDetails;
import com.yanik.todolist.dto.TodoItemDto;
import com.yanik.todolist.model.Todo;
import com.yanik.todolist.model.TodoItem;
import com.yanik.todolist.model.Views;
import com.yanik.todolist.service.ErrorService;
import com.yanik.todolist.service.TodoItemService;

@RestController
@CrossOrigin
@RequestMapping("/api/item")
public class TodoItemController {

	@Autowired
	private TodoItemService todoItemService;
	
	@Autowired
	private ErrorService errorService;

	@GetMapping(value = "/list")
	private TodoItemDto getUserItems(Authentication authentication,
			@RequestParam("page") int page, @RequestParam("sizePerPage") int sizePerPage){
		Pageable pageable = PageRequest.of(page,sizePerPage, Sort.by("createdAt").descending());
		TodoItemDto asd = todoItemService.getUserItems(((CustomUserDetails) authentication.getPrincipal()).getId(),pageable); 
		return asd;
	}
	
    @GetMapping(value = "/getitem/{todoItemId}")
    public ResponseEntity<?> getTodo(@PathVariable Integer todoItemId){	
    	return new ResponseEntity<TodoItem>(todoItemService.getTodoItem(todoItemId), HttpStatus.OK);
    }
	
	@GetMapping(value = "/list/{todoId}")
	@JsonView(Views.UserView.class)
	private List<TodoItem> getUserTodoItem(@PathVariable Integer todoId,Authentication authentication){
		return todoItemService.getTodoItems(todoId, ((CustomUserDetails) authentication.getPrincipal()).getId());
	}
	
	@PostMapping(value = "/add/{todoId}")
	private ResponseEntity<?> userAddTodoItem(@RequestBody TodoItem todoItem,@PathVariable Integer todoId,BindingResult result, Authentication authentication) {
		
        ResponseEntity<?> errorMap = errorService.validationService(result);
        if(errorMap!=null) 
        	return errorMap;
		
		todoItem.setTodo(new Todo(todoId));
        return new ResponseEntity<TodoItem>(todoItemService.saveOrUpdateItem(todoItem), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/delete/{todoItemId}")
	private ResponseEntity<String> userDeleteTodoItem(@PathVariable Integer todoItemId) {
		todoItemService.delete(todoItemId);
        return new ResponseEntity<String>("TodoItem with ID: '"+todoItemId+"' was deleted", HttpStatus.OK);
	}
	
}
