//package com.bet.BettingGame.service;
//import static org.mockito.Mockito.*;
//
//import java.time.LocalDateTime;
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
//public class RaceResultServiceTest {
//
//    @Mock
//    private RaceResultRepository raceResultRepository;
//
//    @InjectMocks
//    private RaceResultServiceImp raceResultService;
//
//    @Test
//    public void testUpdateOrSaveRaceResult() {
//        // Sample data
//        RaceResult raceResult = new RaceResult();
//        LocalDateTime endTime = LocalDateTime.now();
//
//        // Call the method under test
//        raceResultService.updateOrSaveRaceResult(raceResult, endTime);
//
//        // Verify that the repository's method was called with the correct parameters
//        verify(raceResultRepository, times(1)).save(raceResult);
//    }
//}
