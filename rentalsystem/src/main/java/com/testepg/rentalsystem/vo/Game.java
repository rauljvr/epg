package com.testepg.rentalsystem.vo;

import javax.validation.constraints.NotNull;

public class Game {
	
	@NotNull
	private Long gameId;
	
	@NotNull
	private int days;


	public Game() {
		super();
	}

	public Game(Long gameId, int days) {
		super();
		this.gameId = gameId;
		this.days = days;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

}
