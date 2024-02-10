package com.mta.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mta.entities.Interview_Status;

public interface InterviewStatusRepo extends JpaRepository<Interview_Status, Integer> {

}
