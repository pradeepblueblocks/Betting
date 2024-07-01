//package com.bet.BettingGame.repository;
//
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class BetRepositoryTest {
//
//    @Mock
//    private BetRepository betRepository;
//
//
//
//    @Test
//    public void testFindLoginTimeByUserId() {
//        LocalDateTime expectedLoginTime = LocalDateTime.now();
//        Long userId = 1L;
//
//        // Mock the behavior of the repository method
//        when(betRepository.findLoginTimeByUserId(userId)).thenReturn(expectedLoginTime);
//
//        // Call the repository method being tested
//        LocalDateTime actualLoginTime = betRepository.findLoginTimeByUserId(userId);
//
//        // Verify that the returned login time matches the expected login time
//        assertEquals(expectedLoginTime, actualLoginTime);
//    }
//
//    @Test
//    public void testFindHorseIdWithMaxBets() {
//        // Define test data
//        LocalDateTime startTime = LocalDateTime.of(2024, 5, 14, 0, 0);
//        LocalDateTime endTime = LocalDateTime.of(2024, 5, 15, 0, 0);
//        int slotNumber = 1;
//        LocalDate currentDate = LocalDate.of(2024, 5, 14);
//        LocalDateTime currentTime = LocalDateTime.of(2024, 5, 14, 10, 0);
//        List<Long> expectedHorseIds = Arrays.asList(1L, 2L);
//
//        // Mock the behavior of the repository method
//        when(betRepository.findHorseIdWithMaxBets(any(LocalDateTime.class), any(LocalDateTime.class), eq(slotNumber), any(LocalDate.class), any(LocalDateTime.class)))
//                .thenReturn(expectedHorseIds);
//
//        // Call the repository method being tested
//        List<Long> actualHorseIds = betRepository.findHorseIdWithMaxBets(startTime, endTime, slotNumber, currentDate, currentTime);
//
//        // Verify that the returned horse ids match the expected horse ids
//        assertEquals(expectedHorseIds, actualHorseIds);
//    }
//
//}
