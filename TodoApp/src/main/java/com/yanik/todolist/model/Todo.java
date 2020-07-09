package com.yanik.todolist.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@JsonView(Views.UserView.class)
	private Integer id;
	
	@Column(nullable = false, unique = false)
	//@JsonView(Views.UserView.class)
	@NotBlank(message = "Todo name not be blank")
	private String todoName;
	
	@Lob
	//@JsonView(Views.UserView.class)
	@NotBlank(message = "Todo description not be blank")
	private String todoDesc;
	
	@Column(nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	//@JsonView(Views.UserView.class)
	private boolean completed = false;
	
	
	@JsonIgnoreProperties("todos")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	//@JsonView(Views.AdminView.class)
	private User user;
	
	@JsonIgnore
	@JsonIgnoreProperties("todo")
	@OneToMany(mappedBy = "todo", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<TodoItem> todoItems;
	
	@Temporal(TemporalType.TIMESTAMP)
	//@JsonView(Views.UserView.class)
    @Column(nullable=false, updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd / MM / yyyy")
	private Date createdAt;


	public Todo(int id) {
		this.id = id;
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
	
}
