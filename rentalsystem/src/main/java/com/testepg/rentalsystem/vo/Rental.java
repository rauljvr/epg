package com.testepg.rentalsystem.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Rental {
	
	@NotNull
	private Long userId;
	
	@NotNull
	@Valid
	private List<Game> games;
	
	public Rental() {
		super();
	}

	public Rental(Long userId, List<Game> games) {
		super();
		this.userId = userId;
		this.games = games;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	@Override
	public String toString() {
		return "Rental [userId=" + userId + ", games=" + games + "]";
	}

}
