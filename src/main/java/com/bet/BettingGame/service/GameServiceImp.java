package com.bet.BettingGame.service;

import com.bet.BettingGame.model.Game;
import com.bet.BettingGame.repository.BetItemRepository;
import com.bet.BettingGame.repository.BetRepository;
import com.bet.BettingGame.repository.GameRepository;
import com.bet.BettingGame.repository.RaceResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Base64;
import java.util.Optional;

@Slf4j
@Service
public class GameServiceImp implements GameService{
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private BetRepository betRepository;
    @Autowired
    private BetItemRepository betItemRepository;
    @Autowired
    private RaceResultRepository raceResultRepository;

//    @Transactional
//    public void deleteGameById(Long gameId) {
//        // Delete dependent records in the Bet table
////        betRepository.deleteByGameId(gameId);
//
//        // Delete dependent records in the RaceResult table
////        raceResultRepository.deleteByGameId(gameId);
//        betRepository.deleteByGameId(gameId);
//        betItemRepository.deleteByGameId(gameId);
//
//
//        // Then delete the game
//        gameRepository.deleteById(gameId);
//    }

    public void disableGame(Long gameId) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();
            game.setIsActive(0); // Set isActive to 0
            gameRepository.save(game);
        } else {
            throw new EntityNotFoundException("Game not found with ID: " + gameId);
        }
    }
}
