package com.mta.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mta.entities.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{
	
	public Admin findByUserId(int id);
	
	@Query(value = "select * from admin where user_name = ? and role_id = 1 ", nativeQuery = true)
	public Admin getUserByUserName(String str);
	
	@Query(value = "select * from admin where user_name = ? and password = ? and role_id = ? ", nativeQuery = true)
	public Admin findByUserNameAndPasswordWithRoleId(String userName, String password, int id);

	@Query(value = "select * from admin where user_name = ? and password = ? and role_id = ? ", nativeQuery = true)
	public Admin checkUserNameAndPasswordWithRoleName(String userName, String password, int id);
	
	
}
