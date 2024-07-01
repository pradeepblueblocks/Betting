package com.bet.BettingGame.controller;
import com.bet.BettingGame.model.Game;
import com.bet.BettingGame.repository.GameRepository;
import com.bet.BettingGame.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    @Test
    public void testGetAllActiveGames() {
        // Mock data
        List<Game> games = Collections.singletonList(new Game("Test Game", new byte[]{}));
        when(gameRepository.findByIsActive(1)).thenReturn(games);

        // Call controller method
        List<Game> result = gameController.getAllActiveGames();

        // Verify results
        assertEquals(games.size(), result.size());
        assertEquals(games.get(0).getGameName(), result.get(0).getGameName());
        verify(gameRepository, times(1)).findByIsActive(1);
    }

    @Test
    public void testDisableGame_Success() {
        // Mock service method
        Long gameId = 1L;
        doNothing().when(gameService).disableGame(gameId);

        // Call controller method
        ResponseEntity<String> response = gameController.disableGame(gameId);

        // Verify results
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Game disabled successfully", response.getBody());
        verify(gameService, times(1)).disableGame(gameId);
    }

    @Test
    public void testDisableGame_EntityNotFoundException() {
        // Mock service method to throw EntityNotFoundException
        Long gameId = 1L;
        doThrow(new EntityNotFoundException("Game not found")).when(gameService).disableGame(gameId);

        // Call controller method
        ResponseEntity<String> response = gameController.disableGame(gameId);

        // Verify results
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Game not found", response.getBody());
        verify(gameService, times(1)).disableGame(gameId);
    }

    @Test
    public void testDisableGame_InternalServerError() {
        // Mock service method to throw any other exception
        Long gameId = 1L;
        doThrow(new RuntimeException("Internal server error")).when(gameService).disableGame(gameId);

        // Call controller method
        ResponseEntity<String> response = gameController.disableGame(gameId);

        // Verify results
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred while disabling the game", response.getBody());
        verify(gameService, times(1)).disableGame(gameId);
    }

    // Add similar tests for other controller methods...
}
