package com.mta.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity
public class File_Upload {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FILE_UPLOAD_ID")
	private int fileUploadId;
	
	@Column(name = "FILE_NAME",nullable = false,unique=true)
	private String fileName;
	
	@Column(name = "FILE_DATE_TIME",columnDefinition = "TIMESTAMP")
	private LocalDateTime fileDateTime;
	
	@Column(name = "FILE_STATUS")
	private String fileStatus;
	
	 @PrePersist
	    public void prePersist() {
	    	fileDateTime = LocalDateTime.now();
	    }
}
