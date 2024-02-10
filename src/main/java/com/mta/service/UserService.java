package com.mta.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mta.Repo.UserRepo;
import com.mta.entities.Role;
import com.mta.entities.User;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
   
	@Autowired
	private UserRepo repo;

	// --------------- Fetch User by Id ---------------------
	public User getUserById(int id) {
		User usr = repo.findByUserId(id);
		return usr;
	}
	
	// --------------- Fetch User by name ---------------------
	public User getUserByName(String name) {
		User usr = repo.getUserByUserName(name);
		return usr;
	}

	// --------------- Fetch All User ---------------------
	public List<User> getAllUser() {
		List<User> users = repo.findAll();
		return users;
	}

	// --------------- Authenticate User by role_Id ---------------------
	public User chckUser(String usr, String pswd, int roleId) {
		User user = repo.checkUserNameAndPasswordWithRoleId(usr, pswd, roleId);
//		System.out.println(user);
		return user;
	}

	// --------------- Authenticate User by role_name ---------------------
	public User chckUser(String usr, String pswd, String role) {
		int id = role.equals("Admin") ? 1 : 2;
//		System.out.println("id : " + id);
		User user = repo.checkUserNameAndPasswordWithRoleName(usr, pswd, id); //
		System.out.println(user);
		return user;
	}

	// --------------- Add User to database ---------------------
	public void addUser(User usr) {
		repo.save(usr);
	}

	// --------------- Update User by Id ---------------------
	@Transactional
	public User updateUser(User usr) {
		logger.info("Given user  : {}",usr);
		User existUser = repo.findByUserId(usr.getUserId());
		logger.info("existing user available -: {}",existUser);
		if(existUser != null) {
			Role role = new Role();
			role.setRoleId(2);
			usr.setRole(role);
			existUser = repo.save(usr);
			
		}
		return existUser;
//		User user = null;
//		if (usr.getUserId() == id) {
//			user = repo.save(usr);
//		}
//		return user;
	}

	// --------------- Delete User by Id ---------------------
	public Optional<User> delUserById(int id) {
		Optional<User> usr = repo.findById(id);
		if (usr.isPresent()) {

			logger.info("Deleting user with userId: {}", id);
			repo.deleteUserByUserId(id);
			logger.info("User deleted successfully");
//			repo.deleteByUserId(id);
//			repo.deleteById(id);
//			Optional<User> u = repo.findById(id);
//			System.out.println(":" +u);
			return usr;
		} else
			return usr;
	}

	// --------------- Delete All User ---------------------
	public void delAllUser() {
		repo.deleteAllUsers();
	}

}
