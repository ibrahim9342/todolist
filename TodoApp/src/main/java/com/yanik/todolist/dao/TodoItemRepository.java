package com.yanik.todolist.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yanik.todolist.model.Todo;
import com.yanik.todolist.model.TodoItem;

public interface TodoItemRepository extends JpaRepository<TodoItem, Integer> {
	List<TodoItem> findByTodo(Todo todo);
	
	@Query(value = "SELECT i FROM TodoItem i "
			+ "INNER JOIN Todo t ON t.user.id = :userid and t.user.id = :userid "
			+ "WHERE i.todo.id = t.id ")
	Page<TodoItem> findUserItems(@Param("userid") long userId, Pageable pageable);
	
	
	Page<TodoItem> findByTodo(Todo todo,Pageable pageable);
	
	
	@Query("SELECT i FROM TodoItem i "
			+ "INNER JOIN Todo t ON t.user.id = ?2 and t.id = ?1 "
			+ "WHERE i.todo.id = t.id ")
	List<TodoItem> findTodoItems(int todoId,long userId);
	
}
