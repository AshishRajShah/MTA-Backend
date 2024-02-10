package com.mta.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mta.entities.Offer;
import com.mta.service.OfferService;

@RestController
@RequestMapping("/user")
public class OfferController {
	
	@Autowired
	private OfferService dao;
	
	@GetMapping("/offer/{id}")
	public ResponseEntity<Optional<Offer>> getUserById(@PathVariable("id") int id) {
		Optional<Offer> offer = dao.getOfferById(id);
		System.out.println(offer);
		if (offer == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		return ResponseEntity.of(Optional.of(offer));
	}

}
