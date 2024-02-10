package com.mta.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Package_Det {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PACKAGE_ID")
	private int packageId;
	private String ctc;
	private String ectc;
	
	@Column(name = "NOTICE_PERIOD")
	private String noticePeriod;
	
	@Column(name = "PACKAGE_DATE_TIME",columnDefinition = "TIMESTAMP")
	private LocalDateTime packageDateTime;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "Pkg_UserId")
	private List<User> user= new ArrayList<>();
	
	@PrePersist
    public void prePersist() {
		packageDateTime = LocalDateTime.now();
    }
	
}
