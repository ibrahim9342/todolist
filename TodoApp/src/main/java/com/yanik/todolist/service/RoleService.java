package com.yanik.todolist.service;

import java.util.Optional;

import com.yanik.todolist.model.ERole;
import com.yanik.todolist.model.Role;

public interface RoleService {
	Optional<Role> findRoleByName(ERole name);
}
