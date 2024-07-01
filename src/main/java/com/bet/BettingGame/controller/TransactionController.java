package com.bet.BettingGame.controller;

import com.bet.BettingGame.model.*;
import com.bet.BettingGame.repository.BetItemRepository;
import com.bet.BettingGame.repository.GameRepository;
import com.bet.BettingGame.repository.RaceResultRepository;
import com.bet.BettingGame.service.BettingTransactionService;
import com.bet.BettingGame.service.UserBalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementPermission;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

@Autowired
private BettingTransactionService bettingTransactionService;
@Autowired
private GameRepository gameRepository;
@Autowired
private BetItemRepository betItemRepository;
@Autowired
private UserBalanceService userBalanceService;



   @GetMapping("/user/{userId}")
   public ResponseEntity<Object> getTransactionDetailsByUserId(@PathVariable int userId) {
            try {
                List<BettingTransaction> transactions = bettingTransactionService.findTransactionsByuserid(userId);
                if (transactions.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No transactions found for the given user ID.");
                }

                // Fetch user details to get the username
                Optional<UserDetails> userDetailsOptional = bettingTransactionService.findUserDetailsByuserid(userId);
                if (!userDetailsOptional.isPresent()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User details not found for the given user ID.");
                }

                UserDetails userDetails = userDetailsOptional.get();

                // Fetch user balance
//                UserBalance userBalance = userBalanceService.findByUserId(userId);
//                float walletBalance = userBalance != null ? userBalance.getWalletBalance() : 0.0f;



                // Map each BettingTransaction to a BettingTransactionDTO
                List<BettingTransactionDTO> transactionDTOs = transactions.stream().map(transaction -> {
                    BettingTransactionDTO obj = new BettingTransactionDTO();
                    obj.setTransactionId(transaction.getTransactionId());
                    obj.setUserName(userDetails.getUsername()); // Set the username
                    obj.setBetTime(transaction.getBetTime());
                    obj.setSlotNumber(transaction.getSlotNumber());
                    obj.setBetAmount(transaction.getBetAmount());
                    obj.setWinningPrice(transaction.getWinningPrice());
                    obj.setWinningPriceAwarded(transaction.isWinningPriceAwarded());
                    obj.setSlotId(transaction.getSlotId());
                    obj.setWalletBalance(transaction.getWalletBalance());

                    // Determine profit or loss
                    if (transaction.getWinningPrice() == 0.0) {
                        obj.setProfitorLoss("Loss");
                    } else {
                        obj.setProfitorLoss("Profit");
                    }

                    // Fetch the game name using gameId
                    Optional<Game> gameOptional = gameRepository.findById(transaction.getGameId());
                    if (gameOptional.isPresent()) {
                        obj.setGameName(gameOptional.get().getGameName()); // Set the game name
                    } else {
                        obj.setGameName("Unknown"); // Handle the case where the game is not found
                    }


                    // Fetch the bet item name using winningHorseIndex
                    Long winningHorseIndex = transaction.getWinningHorseIndex();
                    if (winningHorseIndex != null) {
                        Optional<BetItem> resultOptional = betItemRepository.findById(winningHorseIndex);
                        if (resultOptional.isPresent()) {
                            obj.setBetItemName(resultOptional.get().getBetItemName()); // Set the bet item name
                        } else {
                            obj.setBetItemName("Unknown"); // Handle the case where the bet item is not found
                        }
                    } else {
                        obj.setBetItemName("Unknown"); // Handle the case where the winningHorseIndex is null
                    }



                    return obj;
                }).collect(Collectors.toList());

                return ResponseEntity.ok(transactionDTOs);
            } catch (Exception e) {
                log.error("An error occurred while fetching transaction details: " + e.getMessage(), e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching transaction details. Please try again later.");
            }
        }
    }


