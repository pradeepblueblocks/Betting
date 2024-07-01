//package com.bet.BettingGame.service;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.bet.BettingGame.model.HorseDetails;
//import com.bet.BettingGame.repository.HorseRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//public class HorseServiceImpTest {
//
//    @Mock
//    private HorseRepository horseRepository;
//
//    @InjectMocks
//    private HorseServiceImp horseService;
//
//    @Test
//    public void testSaveHorse() {
//        // Mocking repository method
//        when(horseRepository.save(any(HorseDetails.class))).thenReturn(new HorseDetails());
//
//        HorseDetails horse = new HorseDetails();
//        HorseDetails savedHorse = horseService.saveHorse(horse);
//
//        // Verifying that the repository's save method was called once
//        verify(horseRepository, times(1)).save(horse);
//        assertEquals(horse, savedHorse);
//    }
//
//    @Test
//    public void testFindIdByName() {
//        // Sample horse name
//        String horseName = "MyHorse";
//        // Sample horse ID
//        Long horseId = 123L;
//
//        // Mocking repository method
//        when(horseRepository.findIdByName(horseName)).thenReturn(horseId);
//
//        Long foundHorseId = horseService.findIdByName(horseName);
//
//        // Verifying that the repository's method was called once
//        verify(horseRepository, times(1)).findIdByName(horseName);
//        assertEquals(horseId, foundHorseId);
//    }
//}
//
