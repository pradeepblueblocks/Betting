package com.bet.BettingGame.controller;
import com.bet.BettingGame.model.*;
import com.bet.BettingGame.repository.GameRepository;

import com.bet.BettingGame.repository.SlotRepository;
import com.bet.BettingGame.service.BetItemService;
import com.bet.BettingGame.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class GameController {


    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private BetItemService betItemService;

    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private GameService gameService;



    @GetMapping("/list/games")
    public List<Game> getAllActiveGames() {
        return gameRepository.findByIsActive(1);
    }

    @PutMapping("/disable/{gameId}")
    public ResponseEntity<String> disableGame(@PathVariable("gameId") Long gameId) {
        try {
            gameService.disableGame(gameId);
            return ResponseEntity.ok("Game disabled successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while disabling the game");
        }
    }



    /////Image is saved in file///////


    @PostMapping(value = "/games", consumes = "application/json")
    public ResponseEntity<Game> createGame(@RequestBody GameRequest gameRequest) {
        try {
            String base64String = gameRequest.getPictureBase64();
            if (base64String == null) {
                // Return a bad request response if pictureBase64 is null
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            byte[] pictureBytes = Base64.getDecoder().decode(base64String);

            // Define the directory where you want to save the image
            String directoryPath = "C:\\Nisy\\pictures";
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs(); // Create directory if it doesn't exist
            }

            // Define the file name and file path
            String fileName = UUID.randomUUID().toString() + ".jpg";
            File file = new File(directory, fileName);

            // Save the image to the file system
            try (OutputStream outputStream = new FileOutputStream(file)) {
                outputStream.write(pictureBytes);
            }

            // Get the file path
            String filePath = file.getAbsolutePath();

            // Create a new Game object and set the file path
            Game game = new Game(gameRequest.getGameName(), filePath);
            game.setIsActive(1);
            Game savedGame = gameRepository.save(game);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedGame);
        } catch (IllegalArgumentException e) {
            // Handle base64 decoding error
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (IOException e) {
            // Handle file writing error
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            // Log the complete stack trace for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/single/game/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable("id") Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/betitems")
    public BetItem createBetItem(@RequestBody BetItemRequest betItemRequest) {
        BetItem betItem = new BetItem();
        betItem.setGameId(betItemRequest.getGameId());
        betItem.setBetItemName(betItemRequest.getBetItemName());
        return betItemService.saveBetItem(betItem);
    }

    @GetMapping("/list/betitems/{gameId}")
    public ResponseEntity<List<BetItemDTO>> getBetItemsByGameId(@PathVariable Long gameId) {
        try {
            List<BetItemDTO> betItems = betItemService.getBetItemsByGameId(gameId);
            return ResponseEntity.ok(betItems);
        } catch (Exception e) {
            log.error("An error occurred while fetching bet items: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }


    @GetMapping("/single/betitem/{id}")
    public Optional<BetItemDTO> getBetItemById(@PathVariable Long id) {

        return betItemService.getBetItemById(id);
    }

}

