package com.backend.todolist.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.todolist.model.ToDoItem;

public interface ToDoItemRepository  extends JpaRepository<ToDoItem, Long>,JpaSpecificationExecutor<ToDoItem> {

	   @Query(value="SELECT i FROM ToDoItem i WHERE i.todolistid = :listid")
	   List<ToDoItem> findAllItems(@Param("listid") Long listid); 
	   
	   @Query(value="SELECT i FROM ToDoItem i WHERE i.todolistid = :listid and i.statu <> :statu and i.itemName LIKE %:iname% ")
	   List<ToDoItem> findAllItemss(@Param("listid") Long listid, 
			   @Param("statu") String statu,
			   @Param("iname") String iname); 

	   @Query(value="SELECT i FROM ToDoItem i WHERE i.todolistid = :listid and i.statu <> :statu"
	   		+ " and :sdate > i.deadLine and i.itemName LIKE %:iname% ")
	   List<ToDoItem> findAllItemsExp(@Param("listid") Long listid, 
			   @Param("statu") String statu,
			   @Param("iname") String iname, 
			   @Param("sdate") Date sdate);    
	   
	   @Query(value="SELECT i FROM ToDoItem i  WHERE i.todolistid = :listid ORDER BY i.createdAt ASC")
	   List<ToDoItem> findAllItemsOrderAsc(@Param("listid") Long listid);    
	   
	   @Query(value="SELECT i FROM ToDoItem i  WHERE i.todolistid = :listid ORDER BY i.createdAt DESC")
	   List<ToDoItem> findAllItemOrderDesc(@Param("listid") Long listid);   
}
 