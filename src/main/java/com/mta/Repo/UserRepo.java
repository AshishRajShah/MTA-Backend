package com.mta.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mta.entities.User;

import jakarta.transaction.Transactional;

public interface UserRepo extends JpaRepository<User, Integer>{

	public User findByUserId(int id);
	
	@Query(value = "select * from user where user_name = ? and role_id = 2 ", nativeQuery = true)
	public User getUserByUserName(String str);
	
	@Transactional 
	@Modifying  // @Modifying is used to indicate that the query modifies the database. 
				//This annotation is required for any query method that modifies the database.
	@Query(value = "Delete from user where basic_id = ? ", nativeQuery = true)
	public void deleteUserByUserId(int id);
	
	@Transactional	// @Transactional ensures that the deletion operation is executed within a transaction.
	@Modifying
	@Query(value = "Delete from user", nativeQuery = true)
	public void deleteAllUsers();
	
	@Query(value = "select * from user where user_name = ? and password = ? and role_id = ? ", nativeQuery = true)
	public User checkUserNameAndPasswordWithRoleId(String userName, String password, int id);

	@Query(value = "select * from user where user_name = ? and password = ? and role_id = ? ", nativeQuery = true)
	public User checkUserNameAndPasswordWithRoleName(String userName, String password, int id);
}
