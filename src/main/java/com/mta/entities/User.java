package com.mta.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@DynamicUpdate
@DynamicInsert
public class User implements UserDetails {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BASIC_ID")
	private int userId;
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;
	@Column(name = "MIDDLE_NAME")
	private String MiddleName;
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;
	
	@Column(name = "DOB", nullable = false,columnDefinition = "DATE")
	private LocalDate dob ;
	@Column(nullable = false)
	private String gender;
	private String mobile;
	@Column(name="USER_NAME",unique = true)
	private String email;
	@Column(name = "CURRENT_LOCATION")
	private String currentLocation;
	@Column(name = "PREFERED_LOCATION")
	private String prefered_location;
	private String country;
	@Column(name = "PASSWORD",nullable=false)
	private String password;
	
    @Column(name = "BASIC_DATE_TIME", updatable = false,columnDefinition = "TIMESTAMP")
	private LocalDateTime basicDateTime; 
    
    @PrePersist
    public void prePersist() {
    	basicDateTime = LocalDateTime.now();
    }
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ROLE_ID")
    private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
