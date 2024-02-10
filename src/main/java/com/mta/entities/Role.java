package com.mta.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicUpdate
//@ToString
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "roleId")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private int roleId;
	@Column(name = "ROLE_NAME", nullable = false,unique=true)
	private String roleName;
	@Column(name = "ROLE_STATUS")
	private String role_status;
	
	@OneToMany(mappedBy = "role",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Admin> admin= new ArrayList<>();
	
	@OneToMany(mappedBy = "role",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<User> user= new ArrayList<>();
	
//	 public Role() {
//	        this.admin = new ArrayList<>();
//	    }
	
	public Role(String roleName) {
        this.roleName = roleName;
    }
//	@OneToMany(mappedBy = "roleId", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Admin> admin = new ArrayList<>();
	
	
}
