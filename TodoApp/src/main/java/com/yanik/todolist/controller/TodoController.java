package com.yanik.todolist.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

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

import com.yanik.todolist.dto.CustomUserDetails;
import com.yanik.todolist.dto.TodoDto;
import com.yanik.todolist.model.Todo;
import com.yanik.todolist.model.User;
import com.yanik.todolist.service.ErrorService;
import com.yanik.todolist.service.TodoService;

@RestController
@CrossOrigin
@RequestMapping("/api/todo")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@Autowired
	private ErrorService errorService;
	
	
    @GetMapping(value = "alllist")
    //@JsonView(Views.AdminView.class)
    //@PreAuthorize("hasRole('USER')")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Todo> getUserTodos(Authentication authentication,Principal principal){
        return todoService.getTodosByUser(((CustomUserDetails) authentication.getPrincipal()).getId());
    }
    

    @GetMapping(value = "list")
    //@PreAuthorize("hasRole('USER')")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public TodoDto getUserTodosPage(Authentication authentication,Principal principal, 
    		@RequestParam("page") int page, @RequestParam("sizePerPage") int sizePerPage){    	
    	Pageable pageable = PageRequest.of(page,sizePerPage, Sort.by("createdAt").descending());
        return todoService.getTodosByUser(((CustomUserDetails) authentication.getPrincipal()).getId(), pageable);
    }
    
    @PostMapping(value = "/add")
    public ResponseEntity<?>  userAddTodo(@Valid @RequestBody Todo todo,BindingResult result, Authentication authentication){
    	
        ResponseEntity<?> errorMap = errorService.validationService(result);
        if(errorMap!=null) 
        	return errorMap;
    	
    	todo.setUser(new User(((CustomUserDetails) authentication.getPrincipal()).getId()));
        return new ResponseEntity<Todo>(todoService.saveOrUpdateTodo(todo), HttpStatus.CREATED);
    }

    @GetMapping(value = "/gettodo/{todoId}")
    public ResponseEntity<?> getTodo(@PathVariable Integer todoId){	
    	return new ResponseEntity<Todo>(todoService.getTodo(todoId), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{todoId}")
    public ResponseEntity<?> deleteProject(@PathVariable Integer todoId){
        todoService.delete(todoId);
        return new ResponseEntity<String>("Todo with ID: '"+todoId+"' was deleted", HttpStatus.OK);
    }
    
//  @GetMapping(value = "listd")
//  @JsonView(Views.AdminView.class)
//  @PreAuthorize("hasRole('USER')")
//  //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//  public List<Deneme> getUserTodosDeneme(){
//  	List<Deneme> dd = todoService.getTodosByUserDeneme(2l);
//      return dd;
//  }
	
}
