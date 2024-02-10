package com.mta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mta.Repo.RoleRepo;
import com.mta.entities.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepo repo;
	
	public Role getRoleById(int id) {
		Role ids = repo.findByRoleId(id);
		System.out.println(ids);
		return ids;
	}
}
