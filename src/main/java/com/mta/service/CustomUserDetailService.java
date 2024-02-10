//package com.mta.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.mta.entities.User;
//
//import lombok.RequiredArgsConstructor;
//
//
//@Service("Customuser")
//@RequiredArgsConstructor
//public class CustomUserDetailService implements UserDetailsService {
//	
//	@Autowired
//	private UserService udao;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////		System.out.println( username +username.contains("2"));
//		User usr = udao.getUserByName(username);
////		System.out.println(usr);
//		
//		if(usr == null) {
//			throw new UsernameNotFoundException("User not found... ");
//		}
//		return usr;
//	}
//}
