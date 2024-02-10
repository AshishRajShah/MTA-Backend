package com.mta.entities;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
public class Interview_Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INTERVIEW_ID")
	private int interviewId;
	@Column(name = "LEVEL_STATUS")
	private String levelstatus;
	
	@Column(name = "INTERVIEW_DATE_TIME",columnDefinition = "TIMESTAMP")
	private LocalDateTime interviewDateTime;
	
	@Column(name = "HACKER_RANK_STATUS")
	private String hacker_Rank_Status;
	
	@OneToOne(cascade = CascadeType.ALL,fetch =  FetchType.EAGER)
	@JoinColumn(name="USER_ID")
	private User user;
	
	
	 @PrePersist
	    public void prePersist() {
		 interviewDateTime = LocalDateTime.now();
	    }
	
}
