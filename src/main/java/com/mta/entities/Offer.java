package com.mta.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class Offer {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OFFER_ID")
	private int offerId;
	
	@Column(name = "OFFER_DATE",columnDefinition = "DATE")
	private LocalDate offerDate;
	
	@Column(name = "OFFER_JOINING_DATE",columnDefinition = "TIMESTAMP")
	private LocalDateTime offerJoiningDate;
	
	@Column(name = "OFFER_STATUS")
	private String offerStatus;
	
	@Column(name = "OFFER_ACCEPT")
	private String offerAccept;
	
	@OneToOne(fetch =  FetchType.EAGER)
	@JoinColumn(name = "User_ID")
	private User basicId;
	
	@PrePersist
    public void prePersist() {
		offerJoiningDate = LocalDateTime.now();
    }

}
