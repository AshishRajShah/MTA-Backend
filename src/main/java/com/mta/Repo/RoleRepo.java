package com.mta.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mta.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
	
	public Role findByRoleId(int id);
	
	@Query(value = "select role_name from role where role_id = ? ", nativeQuery = true)
	public String findRoleNameByRoleId(int id);

}
