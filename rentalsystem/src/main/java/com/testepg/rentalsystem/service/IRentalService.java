package com.testepg.rentalsystem.service;

import java.time.LocalDate;

import com.testepg.rentalsystem.exceptions.ResourceNotFoundException;
import com.testepg.rentalsystem.vo.Rental;

public interface IRentalService {
	
	/**
	 * It creates the rental tracking of each game rented {@link Rental} and calculates the price to be paid
	 * 
	 * @param {@link Rental} with the rental details
	 * @return rental payment
	 * @throws ResourceNotFoundException
	 */
	public int rentingGames(Rental rental) throws ResourceNotFoundException;

	/**
	 * calculate surcharges, where applicable if the customer return the game late
	 * 
	 * @param gameId
	 * @param userId
	 * @param returnDate of the game
	 * @return the amount of the late return fee if applicable
	 * @throws ResourceNotFoundException
	 */
	public long returnGameRented(Long gameId, Long userId, LocalDate returnDate) throws ResourceNotFoundException;

}
