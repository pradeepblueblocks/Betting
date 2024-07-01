//package com.bet.BettingGame.repository;
//
//import com.bet.BettingGame.model.HorseDetails;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class HorseRepositoryTest {
//
//    @Mock
//    private HorseRepository horseRepository;
//
//    @Test
//    public void testFindById() {
//        // Define test data
//        long id = 1L;
//        HorseDetails expectedHorse = new HorseDetails();
//        expectedHorse.setHorseId(id);
//        expectedHorse.setHorseName("Test Horse");
//
//        // Mock the behavior of the repository method
//        when(horseRepository.findById(id)).thenReturn(expectedHorse);
//
//        // Call the repository method being tested
//        HorseDetails actualHorse = horseRepository.findById(id);
//
//        // Verify that the returned horse matches the expected horse
//        assertEquals(expectedHorse, actualHorse);
//    }
//
//    @Test
//    public void testFindIdByName() {
//        // Define test data
//        String horseName = "Test Horse";
//        Long expectedId = 1L;
//
//        // Mock the behavior of the repository method
//        when(horseRepository.findIdByName(eq(horseName))).thenReturn(expectedId);
//
//        // Call the repository method being tested
//        Long actualId = horseRepository.findIdByName(horseName);
//
//        // Verify that the returned id matches the expected id
//        assertEquals(expectedId, actualId);
//    }
//
//    // Add more test methods for other repository methods if needed
//}