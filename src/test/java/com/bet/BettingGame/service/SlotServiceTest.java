//package com.bet.BettingGame.service;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import com.bet.BettingGame.model.Admin;
//import com.bet.BettingGame.model.SlotDetails;
//import com.bet.BettingGame.repository.SlotRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//public class SlotServiceTest {
//
//    @Mock
//    private SlotRepository slotRepository;
//
//    @InjectMocks
//    private SlotServiceImp slotService;
//
//    @Test
//    public void testSaveSlot() {
//        // Sample slot
//        SlotDetails slot = new SlotDetails();
//
//        // Call the method under test
//        slotService.saveSlot(slot);
//
//        // Verify that the repository's save method was called once with the correct parameter
//        verify(slotRepository, times(1)).save(slot);
//    }
//
//    @Test
//    public void testGetSlotByStartTime() {
//        // Sample start time
//        LocalTime startTime = LocalTime.of(10, 0);
//
//        // Mocking repository method
//        SlotDetails slot = new SlotDetails();
//        when(slotRepository.findByStartTime(startTime)).thenReturn(slot);
//
//        SlotDetails foundSlot = slotService.getSlotByStartTime(startTime);
//
//        // Verifying that the repository's findByStartTime method was called once with the correct parameter
//        verify(slotRepository, times(1)).findByStartTime(startTime);
//        assertEquals(slot, foundSlot);
//    }
//
//    @Test
//    public void testDeleteByAdminAndSelectedDate() {
//        // Sample admin and selected date
////        Admin admin = new Admin();
//        LocalDate selectedDate = LocalDate.of(2024, 5, 20);
//
//        // Call the method under test
//        slotService.deleteByAdminAndSelectedDate(admin, selectedDate);
//
//        // Verifying that the repository's deleteByAdminAndSelectedDate method was called once with the correct parameters
//        verify(slotRepository, times(1)).deleteByAdminAndSelectedDate(admin, selectedDate);
//    }
//}
//
