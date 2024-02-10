package com.mta.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mta.entities.Role;
import com.mta.service.RoleService;

@RestController
@RequestMapping("role")
public class RoleController {
	
	@Autowired
	private RoleService dao;
	
	//	----------------		Fetch data from Id		------------------
	@GetMapping("/get/{id}")
	public ResponseEntity<Role> getAdminById(@PathVariable("id") int id) {
		Role role = dao.getRoleById(id);
		System.out.println(role);
		if (role == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		return ResponseEntity.of(Optional.of(role));
	}

}
