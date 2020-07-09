package com.yanik.todolist.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "items")
public class TodoItem{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@JsonView(Views.UserView.class)
	private Integer id;
	
	@NotBlank(message = "Item name not be blank")
	@Column(nullable = false, unique = true)
	//@JsonView(Views.UserView.class)
	private String itemName;
	
	@NotBlank(message = "Item description name not be blank")
	//@Lob
	@Column(columnDefinition = "TEXT", nullable = false,  length=500000)
	//@JsonView(Views.UserView.class)
	private String itemDesc;
	
	@ManyToOne
	@JoinColumn(name = "todoId")
	//@JsonView(Views.AdminView.class)
	private Todo todo;
	
	@Column(nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	//@JsonView(Views.UserView.class)
	private boolean completed = false;
	
	@Temporal(TemporalType.TIMESTAMP)
	//@JsonView(Views.UserView.class)
	@Column(nullable=false, updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd / MM / yyyy")
	private Date createdAt;
	
	@PrePersist
	protected void onCrearedAt() {
		this.createdAt = new Date();
	}
	
}
