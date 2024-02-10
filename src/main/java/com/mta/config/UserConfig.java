package com.mta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mta.jwt.AuthenticationFilter;
import com.mta.jwt.UnauthorizedEntry;
import com.mta.service.UsersDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class UserConfig {
	 
	@Autowired
	private UnauthorizedEntry entry;

	@Autowired
	private AuthenticationFilter filter;
	
	@Autowired
	private UsersDetailsServiceImpl userDetailService;
	
	// Configuring HttpSecurity
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
					.authorizeHttpRequests(
							auth -> auth.requestMatchers("/home","/admin/login","/user/login","/user/add").permitAll()
									.anyRequest().authenticated())
					.exceptionHandling(ex -> ex.authenticationEntryPoint(entry))
					.authenticationProvider(authenticationProvider()) 
		            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
		            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class) 
		            .build(); 
	}

	// Password Encoding ----
    @Bean
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    } 
    
   @Bean
   public AuthenticationProvider authenticationProvider() {
	   DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	   provider.setUserDetailsService(userDetailService);
	   provider.setPasswordEncoder(passwordEncoder());
	   return provider;
   }
    
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
