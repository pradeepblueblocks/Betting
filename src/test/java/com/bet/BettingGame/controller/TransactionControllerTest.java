//package com.bet.BettingGame.controller;
//
//import com.bet.BettingGame.model.*;
//import com.bet.BettingGame.repository.BetItemRepository;
//import com.bet.BettingGame.repository.GameRepository;
//import com.bet.BettingGame.service.BettingTransactionService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.time.LocalDateTime;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class TransactionControllerTest {
//
//    @Mock
//    private BettingTransactionService bettingTransactionService;
//
//    @Mock
//    private GameRepository gameRepository;
//
//    @Mock
//    private BetItemRepository betItemRepository;
//
//    @InjectMocks
//    private TransactionController  transactionController;
//
//    @Test
//    public void testGetTransactionDetailsByUserId_Success() {
//        // Mock data
//        int userId = 1;
//        List<BettingTransaction> transactions = Collections.singletonList(createMockTransaction());
//        when(bettingTransactionService.findTransactionsByuserid(userId)).thenReturn(transactions);
//
//        UserDetails userDetails = new UserDetails();
//        userDetails.setUsername("TestUser");
//        when(bettingTransactionService.findUserDetailsByuserid(userId)).thenReturn(Optional.of(userDetails));
//
//        Game game = new Game("Test Game", new byte[]{});
//        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));
//
//        BetItem betItem = new BetItem();
//        betItem.setBetItemName("Test Bet Item");
//        when(betItemRepository.findById(anyLong())).thenReturn(Optional.of(betItem));
//
//        // Call controller method
//        ResponseEntity<Object> response = transactionController.getTransactionDetailsByUserId(userId);
//
//        // Verify results
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        List<BettingTransactionDTO> result = (List<BettingTransactionDTO>) response.getBody();
//        assertEquals(1, result.size());
//        assertEquals("TestUser", result.get(0).getUserName());
//        assertEquals("Test Game", result.get(0).getGameName());
//        assertEquals("Test Bet Item", result.get(0).getBetItemName());
//        assertEquals("Profit", result.get(0).getProfitorLoss());
//
//        verify(bettingTransactionService, times(1)).findTransactionsByuserid(userId);
//        verify(bettingTransactionService, times(1)).findUserDetailsByuserid(userId);
//        verify(gameRepository, times(1)).findById(anyLong());
//        verify(betItemRepository, times(1)).findById(anyLong());
//    }
//
//    @Test
//    public void testGetTransactionDetailsByUserId_NoTransactionsFound() {
//        // Mock data
//        int userId = 1;
//        when(bettingTransactionService.findTransactionsByuserid(userId)).thenReturn(Collections.emptyList());
//
//        // Call controller method
//        ResponseEntity<Object> response = transactionController.getTransactionDetailsByUserId(userId);
//
//        // Verify results
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//        assertEquals("No transactions found for the given user ID.", response.getBody());
//
//        verify(bettingTransactionService, times(1)).findTransactionsByuserid(userId);
//        verify(bettingTransactionService, never()).findUserDetailsByuserid(userId);
//        verify(gameRepository, never()).findById(anyLong());
//        verify(betItemRepository, never()).findById(anyLong());
//    }
//
//    @Test
//    public void testGetTransactionDetailsByUserId_UserDetailsNotFound() {
//        // Mock data
//        int userId = 1;
//        List<BettingTransaction> transactions = Collections.singletonList(createMockTransaction());
//        when(bettingTransactionService.findTransactionsByuserid(userId)).thenReturn(transactions);
//        when(bettingTransactionService.findUserDetailsByuserid(userId)).thenReturn(Optional.empty());
//
//        // Call controller method
//        ResponseEntity<Object> response = transactionController.getTransactionDetailsByUserId(userId);
//
//        // Verify results
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals("User details not found for the given user ID.", response.getBody());
//
//        verify(bettingTransactionService, times(1)).findTransactionsByuserid(userId);
//        verify(bettingTransactionService, times(1)).findUserDetailsByuserid(userId);
//        verify(gameRepository, never()).findById(anyLong());
//        verify(betItemRepository, never()).findById(anyLong());
//    }
//
//    // Helper method to create a mock BettingTransaction instance
//    private BettingTransaction createMockTransaction() {
//        BettingTransaction transaction = new BettingTransaction();
//        transaction.setTransactionId(1L);
//        transaction.setBetTime(LocalDateTime.parse("2024-06-26T15:08:00"));
//        transaction.setSlotNumber(1);
//        transaction.setBetAmount(100.0f);
//        transaction.setWinningPrice(50.0f);
//        transaction.setWinningPriceAwarded(true);
//        transaction.setSlotId(1L);
//        transaction.setWalletBalance(150.0f);
//        transaction.setGameId(1L);
//        transaction.setWinningHorseIndex(1L);
//        return transaction;
//    }
//}
