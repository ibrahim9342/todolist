package com.backend.todolist.repository;

import com.backend.todolist.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends JpaRepository<user, Long>,JpaSpecificationExecutor<user> {

	   @Query(value="SELECT u FROM user u WHERE u.email = :email and u.password = :password")
	   user findUser(@Param("email") String email,
	    					 @Param("password") String password);

	   
}
