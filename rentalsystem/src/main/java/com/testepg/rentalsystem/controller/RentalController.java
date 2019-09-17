package com.testepg.rentalsystem.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testepg.rentalsystem.Constants;
import com.testepg.rentalsystem.exceptions.ResourceNotFoundException;
import com.testepg.rentalsystem.exceptions.RestNotFoundException;
import com.testepg.rentalsystem.service.IRentalService;
import com.testepg.rentalsystem.vo.Rental;

@Validated
@RestController
@RequestMapping(path = "/")
public class RentalController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalController.class);
	
	private IRentalService rentalService;
	
	@Autowired
	public RentalController(@Lazy IRentalService rentalService) {
		this.rentalService = rentalService;
	}
	
	/**
	 * It creates the rental tracking of each game rented {@link Rental} and calculates the price to be paid
	 * 
	 * @param {@link Rental} with the rental details
	 * @return rental payment
	 */
	@PostMapping(value = Constants.RENTAL, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Integer> rentingGames(@RequestBody(required = true) @Valid Rental rental) {
		
		int totalPrice = 0;
		
		try {
			totalPrice = rentalService.rentingGames(rental);
			LOGGER.info("Rental service finished successfully, " + rental.toString());
		} catch (ResourceNotFoundException e) {
			LOGGER.error("Service rentingGames failed: " + e.getMessage());
			throw new RestNotFoundException(e.getMessage(), e);
		}
		
		return ResponseEntity.ok().body(new Integer(totalPrice));
    }
	
	/**
	 * It calculates surcharges, where applicable if the customer return the game late
	 * 
	 * @param gameId
	 * @param userId
	 * @param returnDate
	 * @return the amount of the late return fee if applicable
	 */
	@GetMapping(Constants.RETURN + "/" + Constants.GAME + "/{gameId}/" + Constants.USER + "/{userId}/{returnDate}")
	public ResponseEntity<Integer> returnGameRented(@PathVariable(name = "gameId") Long gameId, @PathVariable(name = "userId") Long userId, 
								 @PathVariable(name = "returnDate") @DateTimeFormat(pattern = Constants.DATE_PATTERN) LocalDate returnDate) {
		long surcharges = 0;
		
		try {
			surcharges = rentalService.returnGameRented(gameId, userId, returnDate);
			LOGGER.info("Return Game Rented service finished successfully, game ID: " + gameId + ", user ID: " + 
						userId + ", return date: " + returnDate);
		} catch (ResourceNotFoundException e) {
			LOGGER.error("Service returnGameRented failed: " + e.getMessage());
			throw new RestNotFoundException(e.getMessage(), e);
		}
		//ResponseEntity<Integer>(new Integer((int) surcharges), HttpStatus.OK);
		
		return ResponseEntity.ok().body(new Integer((int) surcharges));
    }

}