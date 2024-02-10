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
public class Reports {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REPORT_ID")
	private int reportId;
	@Column(name = "REPORT_NAME",nullable=false,unique= true)
	private String reportName;
	@Column(name = "REPORT_GENERATED_BY")
	private String reportGeneratedBy;
	@Column(name = "REPORTS_DATE_TIME",columnDefinition = "TIMESTAMP")
	private LocalDateTime reportsDateTime;
	
	@PrePersist
    public void prePersist() {
		reportsDateTime = LocalDateTime.now();
    }
}
