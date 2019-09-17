package com.testepg.rentalsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testepg.rentalsystem.model.GameEntity;
import com.testepg.rentalsystem.model.GameUserEntity;
import com.testepg.rentalsystem.model.UserEntity;

@Repository
public interface GameUserRepository extends JpaRepository<GameUserEntity, Long> {
	
	/**
	 * It gets the last rental for the user and game Id given.
	 * 
	 * @param {@link GameEntity} game rented
	 * @param {@link UserEntity} user
	 * @return {@link GameUserEntity}
	 */
	Optional<GameUserEntity> findTopByGameAndUserOrderByIdDesc(GameEntity game, UserEntity user);
}
