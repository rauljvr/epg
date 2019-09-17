package com.testepg.rentalsystem.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testepg.rentalsystem.Constants;
import com.testepg.rentalsystem.exceptions.ResourceNotFoundException;
import com.testepg.rentalsystem.model.GameEntity;
import com.testepg.rentalsystem.model.GameUserEntity;
import com.testepg.rentalsystem.model.UserEntity;
import com.testepg.rentalsystem.repository.GameRepository;
import com.testepg.rentalsystem.repository.GameUserRepository;
import com.testepg.rentalsystem.repository.UserRepository;
import com.testepg.rentalsystem.service.IRentalService;
import com.testepg.rentalsystem.vo.Game;
import com.testepg.rentalsystem.vo.Rental;

@Service
public class RentalService implements IRentalService {
	
	private GameUserRepository gameUserRepository;
	private GameRepository gameRepository;
	private UserRepository userRepository;
	
	@Autowired
	public RentalService(@Lazy GameUserRepository gameUserRepository, @Lazy GameRepository gameRepository,
			@Lazy UserRepository userRepository) {
		
		this.gameUserRepository = gameUserRepository;
		this.gameRepository = gameRepository;
		this.userRepository = userRepository;
	}
	
	@Transactional
	@Override
	public int rentingGames(Rental rental) throws ResourceNotFoundException {
		
		int upFrontPayment = 0;
			
		UserEntity userE = userRepository.findById(rental.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", rental.getUserId()));
		
		for(Game game : rental.getGames()) {
			GameEntity gameE = gameRepository.findById(game.getGameId())
					.orElseThrow(() -> new ResourceNotFoundException("Game", "id", game.getGameId()));
			
			upFrontPayment += generateRental(gameE, userE, game.getDays());
		}

		return upFrontPayment;
	}
	
	private int generateRental(GameEntity gameEntity, UserEntity userEntity, int days) {
	
		int price, basePrice, overDays;
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusDays(days);
		
		GameUserEntity gameUser = new GameUserEntity();
		gameUser.setStartDate(startDate);
		gameUser.setEndDate(endDate);
		gameUser.setGame(gameEntity);
		gameUser.setUser(userEntity);
		
		basePrice = gameEntity.getType().getPrice();
		
		if(gameEntity.getType().getId().equals(Constants.NEW_RELEASE)) {
			gameUser.setPoints(Constants.NEW_RELEASE_POINTS);
			price = basePrice * days;
		}else {
			gameUser.setPoints(Constants.OTHER_TYPES_POINTS);
			if(days < gameEntity.getType().getDays())
				price = basePrice;
			else {
				overDays = days - gameEntity.getType().getDays();
				price = basePrice + (basePrice * overDays);
			}
		}
		
		gameUserRepository.save(gameUser);
		
		return price;
	}

	@Override
	public long returnGameRented(Long gameId, Long userId, LocalDate returnDate) throws ResourceNotFoundException {
		
		long daysDelayed, surcharges = 0;
		
		GameEntity game = gameRepository.findById(gameId)
				.orElseThrow(() -> new ResourceNotFoundException("Game", "id", gameId));
		
		UserEntity user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		GameUserEntity gameUser = gameUserRepository.findTopByGameAndUserOrderByIdDesc(game, user)
				.orElseThrow(() -> new ResourceNotFoundException("Game rented ", "id", gameId));
		
		if(gameUser.getEndDate().isBefore(returnDate)) {
			daysDelayed = ChronoUnit.DAYS.between(gameUser.getEndDate(), returnDate);
			surcharges = Long.valueOf(gameUser.getGame().getType().getPrice()) * daysDelayed;
		}
		
		return surcharges;
	}
}
