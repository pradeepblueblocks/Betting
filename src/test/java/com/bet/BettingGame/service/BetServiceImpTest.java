//package com.bet.BettingGame.service;
//import com.bet.BettingGame.model.Bet;
//import com.bet.BettingGame.repository.BetRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class BetServiceImpTest {
//
//    @Mock
//    private BetRepository betRepository;
//
//    @InjectMocks
//    private BetServiceImp betService;
//
//
//    @Test
//    public void saveBet_success() {
//        LocalDateTime loginTime = LocalDateTime.of(2024, 5, 22, 14, 53, 15, 13_000_000);
//        Bet bet = new Bet();
//        bet.setUserid(7);
//        bet.setBetItemId(1L);
//        bet.setBetAmount(100.0f);
//        bet.setLogin_time(loginTime);
//        bet.setSlotNumber(5);
//
//        Bet savedBet = betService.saveBet(bet);
//
//        verify(betRepository).save(bet);
//        assertEquals(bet, savedBet);
//    }
//
//    @Test
//    public void saveBet_failure() {
//        LocalDateTime loginTime = LocalDateTime.of(2024, 5, 22, 14, 53, 15, 13_000_000);
//        Bet bet = new Bet();
//        bet.setUserid(7);
//        bet.setBetItemId(1L);
//        bet.setBetAmount(100.0f);
//        bet.setLogin_time(loginTime);
//        bet.setSlotNumber(5);
//
//        doThrow(new RuntimeException("Failed to save")).when(betRepository).save(bet);
//
//        assertThrows(RuntimeException.class, () -> {
//            betService.saveBet(bet);
//        });
//    }
//
//
//
//    @Test
//    public void findHorseIdWithMaxBets_success() {
//        LocalDateTime startDateTime = LocalDateTime.now().minusHours(1);
//        LocalDateTime endDateTime = LocalDateTime.now();
//        int slotNumber = 1;
//        LocalDate currentDate = LocalDate.now();
//        LocalDateTime currentTime = LocalDateTime.now();
//
//        when(betRepository.findHorseIdWithMaxBets(startDateTime, endDateTime, slotNumber, currentDate, currentTime))
//                .thenReturn(Collections.singletonList(2L));
//
//        Long horseId = betService.findHorseIdWithMaxBets(startDateTime, endDateTime, slotNumber, currentDate, currentTime);
//
//        assertEquals(2L, horseId);
//    }
//
//    @Test
//    public void findHorseIdWithMaxBets_failure() {
//        LocalDateTime startDateTime = LocalDateTime.now().minusHours(1);
//        LocalDateTime endDateTime = LocalDateTime.now();
//        int slotNumber = 1;
//        LocalDate currentDate = LocalDate.now();
//        LocalDateTime currentTime = LocalDateTime.now();
//
//        when(betRepository.findHorseIdWithMaxBets(startDateTime, endDateTime, slotNumber, currentDate, currentTime))
//                .thenThrow(new RuntimeException("Failed to find horse ID with max bets"));
//
//        assertThrows(RuntimeException.class, () -> {
//            betService.findHorseIdWithMaxBets(startDateTime, endDateTime, slotNumber, currentDate, currentTime);
//        });
//    }
//
//    @Test
//    public void findBetsByHorseIdAndSlotTime_success() {
//        Long horseId = 1L;
//        LocalDateTime startDateTime = LocalDateTime.now().minusHours(1);
//        LocalDateTime endDateTime = LocalDateTime.now();
//        Bet bet = new Bet();
//        bet.setBetItemId(horseId);
//
//        when(betRepository.findBetsByHorseIdAndSlotTime(horseId, startDateTime, endDateTime))
//                .thenReturn(Collections.singletonList(bet));
//
//        List<Bet> bets = betService.findBetsByHorseIdAndSlotTime(horseId, startDateTime, endDateTime);
//
//        assertEquals(1, bets.size());
//        assertEquals(horseId, bets.get(0).getBetItem());
//    }
//
//    @Test
//    public void findLoginTimeByUserId_success() {
//        Long userId = 1L;
//        LocalDateTime loginTime = LocalDateTime.now();
//
//        when(betRepository.findLoginTimeByUserId(userId)).thenReturn(loginTime);
//
//        LocalDateTime foundLoginTime = betService.findLoginTimeByUserId(userId);
//
//        assertEquals(loginTime, foundLoginTime);
//    }
//
//    @Test
//    public void findLoginTimeByUserId_failure() {
//        Long userId = 1L;
//
//        when(betRepository.findLoginTimeByUserId(userId)).thenThrow(new RuntimeException("Failed to find login time by user ID"));
//
//        assertThrows(RuntimeException.class, () -> {
//            betService.findLoginTimeByUserId(userId);
//        });
//    }
//}