package com.mta.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mta.Repo.AdminRepo;
import com.mta.entities.Admin;
import com.mta.entities.Role;

import jakarta.transaction.Transactional;

@Service
public class AdminService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private AdminRepo repo;

	public Admin getAdminById(int id) {
		Admin admin = repo.findByUserId(id);
		return admin;
	}

	public Admin getAdminByName(String name) {
		Admin admin = repo.getUserByUserName(name);
		return admin;
	}
	
	public Admin chckAdmin(String usr, String pswd, int roleId) {
		Admin admin = repo.findByUserNameAndPasswordWithRoleId(usr, pswd, roleId); //
//		System.out.println(admin);
		return admin;
	}

	public Admin chckAdmin(String usr, String pswd, String role) {
		int id = role.equals("Admin") ? 1 : 2;
//		System.out.println("id : " + id);
		Admin admin = repo.checkUserNameAndPasswordWithRoleName(usr, pswd, id); //
//		System.out.println(admin);
		return admin;
	}
	
	// --------------- Add User  ---------------------
	public Admin addAdmin(Admin admin) {
		return repo.save(admin);
	}

	
	// --------------- Update User  ---------------------
		@Transactional
		public Admin updateUser(Admin usr) {
			logger.info("Given user  : {}",usr);
			Admin existUser = repo.findByUserId(usr.getUserId());
			logger.info("existing user available -: {}",existUser);
			if(existUser != null) {
				Role role = new Role();
				role.setRoleId(1);
				usr.setRole(role);
				existUser = repo.save(usr);
				
			}
			return existUser;
//			User user = null;
//			if (usr.getUserId() == id) {
//				user = repo.save(usr);
//			}
//			return user;
		}
}
