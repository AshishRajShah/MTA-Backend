package com.mta.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mta.entities.Admin;
import com.mta.jwt.Helper;
import com.mta.jwt.JwtResponse;
import com.mta.service.AdminService;
import com.mta.service.UsersDetailsServiceImpl;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

	@Autowired
	private AdminService dao;
	
	@Autowired
	private Helper helper;
	
	@Autowired
	private PasswordEncoder pswdEncoder;
	
	@Autowired
	private UsersDetailsServiceImpl userDetailService;
	
	@Autowired
    private AuthenticationManager authenticationManager;

	/*
	 * @PostMapping("/auths") public ResponseEntity<Admin>
	 * authentications(@RequestBody Admin log) { try { // log.ge Admin chck =
	 * dao.chckAdmin(log.getEmail(), log.getPassword(),
	 * log.getRoleId().getRoleId()); if (chck == null) return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); else return
	 * ResponseEntity.of(Optional.of(chck)); } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } // }
	 */
	
	// ----------------		 Authentication Check  and Login with token..	------------------
	
	@PostMapping("/login") 
    public JwtResponse authenticateAndGetToken(@RequestBody Admin usr) throws AuthenticationException { 
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usr.getEmail(), usr.getPassword())); 
        if (authenticate.isAuthenticated()) { 
        	Admin admin = (Admin) userDetailService.loadUserByUsername(usr.getEmail());
        	String token = helper.generateTokenForAdmin(admin);
//        	System.out.println("controller : " + admin);
//        	System.out.println(token);
            return (new JwtResponse(admin,token));
        } else { 
            throw new UsernameNotFoundException("invalid user request !"); 
        } 
    } 

	// ----------------		 Authentication Check  and Login		------------------
	@PostMapping("/auth")
	public ResponseEntity<Admin> authentication(@RequestBody Admin log) {
		try {
			Admin chck = dao.chckAdmin(log.getEmail(), log.getPassword(), log.getRole().getRoleName());
			System.out.println(log.getRole().getRoleName());
//			System.out.println("controller --- "+chck);
			if (chck == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			else
				return ResponseEntity.of(Optional.of(chck));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	//	----------------		Fetch Admin data from Id		------------------
	@GetMapping("/get/{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable("id") int id) {
		Admin adminById = dao.getAdminById(id);

		System.out.println(adminById);
		if (adminById == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		return ResponseEntity.of(Optional.of(adminById));
	}
	
	
	// ---------------- Add Admin User data to the database ------------------
	@PostMapping("/add")
	public ResponseEntity<String> addUser(@RequestBody Admin usr) {
		try {
			if(dao.getAdminByName(usr.getEmail())!= null) {
				return new ResponseEntity<>("User already exist", HttpStatus.FOUND);
			}
			usr.setPassword(pswdEncoder.encode(usr.getPassword()));
			System.out.println(usr.getPassword());
			dao.addAdmin(usr);				
			
			return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	// ---------------- Update User data to the database ------------------
		@PutMapping("/update")
		public ResponseEntity<Admin> updateUser(@RequestBody Admin usr) {
			
			try {
				usr.setPassword(pswdEncoder.encode(usr.getPassword()));
				dao.updateUser(usr);
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();	
			}catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
	 
	


}
