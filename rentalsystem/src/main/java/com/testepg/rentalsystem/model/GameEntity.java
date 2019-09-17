package com.testepg.rentalsystem.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Game")
@Table(name = "Game")
public class GameEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GAME_ID")
	private Long gameId;
	
	@Column(length = 45)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "TYPE_ID", nullable = false)
	private TypeEntity type;
	
	@OneToMany(mappedBy = "game")
	private List<GameUserEntity> rents;

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TypeEntity getType() {
		return type;
	}

	public void setType(TypeEntity type) {
		this.type = type;
	}

	public List<GameUserEntity> getRents() {
		return rents;
	}

	public void setRents(List<GameUserEntity> rents) {
		this.rents = rents;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameEntity other = (GameEntity) obj;
		if (gameId == null) {
			if (other.gameId != null)
				return false;
		} else if (!gameId.equals(other.gameId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GameEntity [gameId=" + gameId + ", name=" + name + ", type=" + type + ", rents=" + rents + "]";
	}
	
}
