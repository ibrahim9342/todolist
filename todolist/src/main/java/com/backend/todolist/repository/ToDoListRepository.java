package com.backend.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.todolist.model.ToDoList;


public interface ToDoListRepository extends JpaRepository<ToDoList, Long>,JpaSpecificationExecutor<ToDoList> {
	
	   @Query(value="SELECT t FROM ToDoList t WHERE t.userid = :userid")
	   List<ToDoList> findAllToDo(@Param("userid") String userid);
	
}