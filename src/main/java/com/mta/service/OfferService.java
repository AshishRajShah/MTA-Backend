package com.mta.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mta.Repo.OfferRepo;
import com.mta.entities.Offer;

@Service
public class OfferService {
	
	@Autowired
	private OfferRepo repo;
	
	public Optional<Offer> getOfferById(int id) {
		Optional<Offer> offer = repo.findById(id);
		return offer;
	}

}
