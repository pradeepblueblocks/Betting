package com.bet.BettingGame.controller;

import com.bet.BettingGame.model.*;
import com.bet.BettingGame.repository.SlotDetailsRepository;
import com.bet.BettingGame.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private SlotDetailsService slotDetailsService;
    @Mock
    private UserBalanceService userBalanceService;
    @Mock
    private BettingTransactionService bettingTransactionService;
    @Mock
    private BetService betService;
    @Mock
    private SlotDetailsRepository slotDetailsRepository;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetUserPage_Success() {
        // Mock data
        SlotDetailsRequest slotDetailsRequest = new SlotDetailsRequest();
        slotDetailsRequest.setGameId(1L);

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        List<SlotDetails> mockUpcomingSlots = Collections.singletonList(new SlotDetails(
                1L, // slotId
                10, // totalSlots
                1, // slotNumber
                currentTime, // startTime
                currentTime.plusHours(1), // endTime
                currentDate, // selectedDate
                1 // admin
        ));

        when(slotDetailsRepository.findSlotsForCurrentDateAndGameId(any(LocalDate.class), any(LocalTime.class), any(Long.class)))
                .thenReturn(mockUpcomingSlots);

        // Call controller method
        ResponseEntity<Map<String, Object>> responseEntity = userController.getUserPage(slotDetailsRequest);

        // Verify results
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Map<String, Object> responseBody = responseEntity.getBody();
        assertEquals(1, ((List<SlotDetailsDTO>) responseBody.get("slots")).size());

        verify(slotDetailsRepository, times(1)).findSlotsForCurrentDateAndGameId(any(LocalDate.class), any(LocalTime.class), any(Long.class));
    }

    @Test
    public void testGetUserPage_InternalServerError() {
        // Mocking an exception scenario
        SlotDetailsRequest slotDetailsRequest = new SlotDetailsRequest();
        slotDetailsRequest.setGameId(1L);

        when(slotDetailsRepository.findSlotsForCurrentDateAndGameId(any(LocalDate.class), any(LocalTime.class), any(Long.class)))
                .thenThrow(new RuntimeException("Database error"));

        // Call controller method
        ResponseEntity<Map<String, Object>> responseEntity = userController.getUserPage(slotDetailsRequest);

        // Verify results
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("An error occurred while fetching slot details.", responseEntity.getBody().get("error"));

        verify(slotDetailsRepository, times(1)).findSlotsForCurrentDateAndGameId(any(LocalDate.class), any(LocalTime.class), any(Long.class));
    }



    @Test
    public void testSaveBet_Success() {
        // Mock data
        BetRequest betRequest = new BetRequest();
        betRequest.setBetAmount(50.0f);
        betRequest.setSelectedHorse(1L);
        betRequest.setUserid(123);
        betRequest.setSlotNumber(1);
        betRequest.setGameId(1L);

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime currentTime = currentDateTime.toLocalTime();
        SlotDetails slotDetails = new SlotDetails(1L, 10, 1, currentTime.minusMinutes(10), currentTime.plusMinutes(10), currentDateTime.toLocalDate(), 1);

        when(slotDetailsService.findSlotsByNumber(anyInt())).thenReturn(Collections.singletonList(slotDetails));

        UserBalance userBalance = new UserBalance();
        userBalance.setUserId(123);
        userBalance.setWalletBalance(100.0f);

        when(userBalanceService.findByUserId(anyInt())).thenReturn(userBalance);

        when(betService.saveBet(any(Bet.class))).thenAnswer(invocation -> {
            Bet bet = invocation.getArgument(0);
            bet.setBetId(1L); // Mocking the setting of BetId after saving
            return bet;
        });

        // Call controller method
        ResponseEntity<String> responseEntity = userController.saveBet(betRequest);

        // Verify results
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Bet saved successfully!", responseEntity.getBody());

        verify(slotDetailsService, times(1)).findSlotsByNumber(anyInt());
        verify(userBalanceService, times(1)).findByUserId(anyInt());
        verify(betService, times(1)).saveBet(any(Bet.class));
        verify(bettingTransactionService, times(1)).saveTransaction(any(BettingTransaction.class));
    }


    @Test
    public void testSaveBet_InvalidSlotNumber() {
        BetRequest betRequest = new BetRequest();
        betRequest.setBetAmount(50.0f);
        betRequest.setSelectedHorse(1L);
        betRequest.setUserid(123);
        betRequest.setSlotNumber(1);
        betRequest.setGameId(1L);

        when(slotDetailsService.findSlotsByNumber(anyInt())).thenReturn(Collections.emptyList());

        // Call controller method
        ResponseEntity<String> responseEntity = userController.saveBet(betRequest);

        // Verify results
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid slot number", responseEntity.getBody());

        verify(slotDetailsService, times(1)).findSlotsByNumber(anyInt());
        verify(userBalanceService, never()).findByUserId(anyInt());
        verify(betService, never()).saveBet(any(Bet.class));
        verify(bettingTransactionService, never()).saveTransaction(any(BettingTransaction.class));
    }

    @Test
    public void testSaveBet_BettingTimeOver() {
        BetRequest betRequest = new BetRequest();
        betRequest.setBetAmount(50.0f);
        betRequest.setSelectedHorse(1L);
        betRequest.setUserid(123);
        betRequest.setSlotNumber(1);
        betRequest.setGameId(1L);

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime currentTime = currentDateTime.toLocalTime();
        SlotDetails slotDetails = new SlotDetails(1L, 10, 1, currentTime.minusHours(2), currentTime.minusHours(1), currentDateTime.toLocalDate(), 1);

        when(slotDetailsService.findSlotsByNumber(anyInt())).thenReturn(Collections.singletonList(slotDetails));

        // Call controller method
        ResponseEntity<String> responseEntity = userController.saveBet(betRequest);

        // Verify results
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Betting time is over for the selected slot number", responseEntity.getBody());

        verify(slotDetailsService, times(1)).findSlotsByNumber(anyInt());
        verify(userBalanceService, never()).findByUserId(anyInt());
        verify(betService, never()).saveBet(any(Bet.class));
        verify(bettingTransactionService, never()).saveTransaction(any(BettingTransaction.class));
    }

    @Test
    public void testSaveBet_InsufficientBalance() {
        BetRequest betRequest = new BetRequest();
        betRequest.setBetAmount(150.0f);
        betRequest.setSelectedHorse(1L);
        betRequest.setUserid(123);
        betRequest.setSlotNumber(1);
        betRequest.setGameId(1L);

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime currentTime = currentDateTime.toLocalTime();
        SlotDetails slotDetails = new SlotDetails(1L, 10, 1, currentTime.minusMinutes(10), currentTime.plusMinutes(10), currentDateTime.toLocalDate(), 1);

        when(slotDetailsService.findSlotsByNumber(anyInt())).thenReturn(Collections.singletonList(slotDetails));
        UserBalance userBalance = new UserBalance();
        userBalance.setUserId(123);
        userBalance.setWalletBalance(100.0f);
        when(userBalanceService.findByUserId(anyInt())).thenReturn(userBalance);

        // Call controller method
        ResponseEntity<String> responseEntity = userController.saveBet(betRequest);

        // Verify results
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Insufficient balance. Please add funds to your wallet.", responseEntity.getBody());

        verify(slotDetailsService, times(1)).findSlotsByNumber(anyInt());
        verify(userBalanceService, times(1)).findByUserId(anyInt());
        verify(betService, never()).saveBet(any(Bet.class));
        verify(bettingTransactionService, never()).saveTransaction(any(BettingTransaction.class));
    }


    @Test
    public void testSaveBet_InternalServerError() {
        BetRequest betRequest = new BetRequest();
        betRequest.setBetAmount(50.0f);
        betRequest.setSelectedHorse(1L);
        betRequest.setUserid(123);
        betRequest.setSlotNumber(1);
        betRequest.setGameId(1L);

        when(slotDetailsService.findSlotsByNumber(anyInt())).thenThrow(new RuntimeException("Database error"));

        // Call controller method
        ResponseEntity<String> responseEntity = userController.saveBet(betRequest);

        // Verify results
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("An error occurred while saving the bet", responseEntity.getBody());

        verify(slotDetailsService, times(1)).findSlotsByNumber(anyInt());
        verify(userBalanceService, never()).findByUserId(anyInt());
        verify(betService, never()).saveBet(any(Bet.class));
        verify(bettingTransactionService, never()).saveTransaction(any(BettingTransaction.class));
    }
}
