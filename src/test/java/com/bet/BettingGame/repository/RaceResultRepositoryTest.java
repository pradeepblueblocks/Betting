//package com.bet.BettingGame.repository;
//
//import com.bet.BettingGame.model.RaceResult;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Pageable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class RaceResultRepositoryTest {
//
//    @Mock
//    private RaceResultRepository raceResultRepository;
//
////    @Test
////    public void testFindLast5Results() {
////        // Define test data
////        List<Object[]> expectedResult = new ArrayList<>();
////        // Add sample data to the expected result
////        // For example:
////        // expectedResult.add(new Object[]{winningHorseIndex, betTime, slotNumber, userid});
////
////        // Call the repository method being tested
////        List<Object[]> actualResult = raceResultRepository.findLast5Results(null); // You may need to provide a Pageable object
////
////        // Verify that the returned result matches the expected result
////        assertEquals(expectedResult, actualResult);
////    }
//
//    @Test
//    public void testFindBySlotIdAndSlotNumberAndUserid() {
//        // Define test data
//        Long slotId = 1L;
//        int slotNumber = 1;
//        int userId = 1;
//        RaceResult expectedResult = new RaceResult(); // Define expected result
//
//        // Mock the behavior of the repository method
//        when(raceResultRepository.findBySlotIdAndSlotNumberAndUserid(slotId, slotNumber, userId)).thenReturn(java.util.Optional.of(expectedResult));
//
//        // Call the repository method being tested
//        java.util.Optional<RaceResult> actualResult = raceResultRepository.findBySlotIdAndSlotNumberAndUserid(slotId, slotNumber, userId);
//
//        // Verify that the returned result matches the expected result
//        assertEquals(expectedResult, actualResult.get());
//    }
//
//    // Add more test methods for other repository methods if needed
//}