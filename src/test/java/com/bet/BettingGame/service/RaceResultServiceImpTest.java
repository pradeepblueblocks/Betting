//package com.bet.BettingGame.service;
//import static org.mockito.Mockito.*;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import com.bet.BettingGame.model.RaceResult;
//import com.bet.BettingGame.repository.RaceResultRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//public class RaceResultServiceImpTest {
//
//    @Mock
//    private RaceResultRepository raceResultRepository;
//
//    @InjectMocks
//    private RaceResultServiceImp raceResultService;
//
//    @Test
//    public void testUpdateOrSaveRaceResult_ExistingPreviousRaceResult() {
//        // Sample data
//        RaceResult raceResult = new RaceResult();
//        raceResult.setSlotId(1L);
//        raceResult.setSlotNumber(2);
//        raceResult.setUserid(123);
//        raceResult.setBetTime(LocalDateTime.now());
//        raceResult.setWinningHorseIndex(1L);
//
//        // Mocking repository method
//        RaceResult existingPreviousRaceResult = new RaceResult();
//        when(raceResultRepository.findBySlotIdAndSlotNumberAndUserid(raceResult.getSlotId(), raceResult.getSlotNumber() - 1, raceResult.getUserid()))
//                .thenReturn(Optional.of(existingPreviousRaceResult));
//
//        // Call the method under test
//        raceResultService.updateOrSaveRaceResult(raceResult, LocalDateTime.now().minusMinutes(1));
//
//        // Verify that the repository's save method was called once with the updated existing race result
//        verify(raceResultRepository, times(1)).save(existingPreviousRaceResult);
//    }
//
//    @Test
//    public void testUpdateOrSaveRaceResult_NoExistingPreviousRaceResult() {
//        // Sample data
//        RaceResult raceResult = new RaceResult();
//        raceResult.setSlotId(1L);
//        raceResult.setSlotNumber(2);
//        raceResult.setUserid(123);
//        raceResult.setBetTime(LocalDateTime.now());
//        raceResult.setWinningHorseIndex(1L);
//
//        // Mocking repository method
//        when(raceResultRepository.findBySlotIdAndSlotNumberAndUserid(raceResult.getSlotId(), raceResult.getSlotNumber() - 1, raceResult.getUserid()))
//                .thenReturn(Optional.empty());
//
//        // Call the method under test
//        raceResultService.updateOrSaveRaceResult(raceResult, LocalDateTime.now().minusMinutes(1));
//
//        // Verify that the repository's save method was called once with the new race result
//        verify(raceResultRepository, times(1)).save(raceResult);
//    }
//
//    // Add more test cases as needed to cover other scenarios
//}
//
