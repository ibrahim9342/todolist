package com.yanik.todolist.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yanik.todolist.dto.Deneme;
import com.yanik.todolist.model.Todo;
import com.yanik.todolist.model.User;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	
	List<Todo> findByUser(User user);
	
    Page<Todo> findByUser(User user, Pageable pageable);
	
	@Query("SELECT new com.yanik.todolist.dto.Deneme(t.todoName) FROM Todo t WHERE  t.user.id = :#{#user.id}")  
	List<Deneme> getxxDeneme(@Param("user") User user); 
}
