package com.mta.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mta.Repo.AdminRepo;
import com.mta.Repo.RoleRepo;
import com.mta.Repo.UserRepo;
import com.mta.entities.Admin;
import com.mta.entities.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;
    
    @Autowired
    private AdminRepo  adminRepository;

    @Autowired
    private RoleRepo roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Admin admin = adminRepository.getUserByUserName(username);
//        System.out.println("admin - "+admin);
        User user = userRepository.getUserByUserName(username);
//        System.out.println("user -"+user);
        
        if (admin == null && user == null  ) {
        	throw new UsernameNotFoundException("User not found");
        }
        
        String role = null;
        if (user != null) {
            role = roleRepository.findRoleNameByRoleId(user.getRole().getRoleId());
        } else if (admin != null) {
            role = roleRepository.findRoleNameByRoleId(admin.getRole().getRoleId());
        }

//        System.out.println("role -"+role);

        if (role.equalsIgnoreCase("admin")) {
//        	System.out.println("if block - "+ admin);
           	return admin;
        } else {
//        	System.out.println("if block - "+ user);
        	return user;
        }
    }
}