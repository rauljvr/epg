package com.testepg.rentalsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testepg.rentalsystem.model.GameEntity;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

}
