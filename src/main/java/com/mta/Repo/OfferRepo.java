package com.mta.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mta.entities.Offer;

public interface OfferRepo extends JpaRepository<Offer, Integer>{

}
