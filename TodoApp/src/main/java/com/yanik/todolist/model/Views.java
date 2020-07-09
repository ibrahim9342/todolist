package com.yanik.todolist.model;

public class Views {

	public static class UserView {}
	
	public static class AdminView extends UserView{}
	
	public static class SuperAdminView extends AdminView{}
	
}
