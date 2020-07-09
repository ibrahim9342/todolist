package com.yanik.todolist.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanik.todolist.dao.RoleRepository;
import com.yanik.todolist.model.ERole;
import com.yanik.todolist.model.Role;
import com.yanik.todolist.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Optional<Role> findRoleByName(ERole name) {
		return roleRepository.findByName(name);
	}

	
}
