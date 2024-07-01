package com.bet.BettingGame.repository;

import com.bet.BettingGame.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByIsActive(int i);
}
