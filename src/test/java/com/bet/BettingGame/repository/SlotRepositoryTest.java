//package com.bet.BettingGame.repository;
//
//import com.bet.BettingGame.model.SlotDetails;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.test.context.jdbc.Sql;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Sql(scripts = "/test-data.sql")
//class SlotRepositoryTest {
//
//    @Autowired
//    private SlotDetailsRepository slotRepository;
//
//    @BeforeEach
//    void setUp() {
//        // Add setup code if necessary
//    }
//
//    @Test
//    void testFindSlotsForCurrentDate() {
//        LocalDate currentDate = LocalDate.of(2024, 5, 15);
//        LocalTime currentTime = LocalTime.of(10, 0);
//        List<SlotDetails> slots = slotRepository.findSlotsForCurrentDate(currentDate, currentTime);
//        assertNotNull(slots);
//        assertFalse(slots.isEmpty());
//    }
//
//    @Test
//    void testFindByStartTime() {
//        LocalTime startTime = LocalTime.of(10, 0);
//        SlotDetails slot = slotRepository.findByStartTime(startTime);
//        assertNotNull(slot);
//        assertEquals(startTime, slot.getStartTime());
//    }
//
//    @Test
//    void testDeleteByAdminAndSelectedDate() {
//        Long adminId = 1L;
//        LocalDate selectedDate = LocalDate.of(2024, 5, 15);
//        slotRepository.deleteByAdminAndSelectedDate(adminId, selectedDate);
//        List<SlotDetails> slots = slotRepository.findBySlotNumber(1);
//        assertTrue(slots.isEmpty());
//    }
//
//    @Test
//    void testFindBySlotNumber() {
//        int slotNumber = 1;
//        List<SlotDetails> slots = slotRepository.findBySlotNumber(slotNumber);
//        assertNotNull(slots);
//        assertFalse(slots.isEmpty());
//        assertEquals(slotNumber, slots.get(0).getSlotNumber());
//    }
//
//    @Test
//    void testFindMaxSlotNumberByAdmin() {
//        Long adminId = 1L;
//        Integer maxSlotNumber = slotRepository.findMaxSlotNumberByAdmin(adminId);
//        assertNotNull(maxSlotNumber);
//        assertEquals(3, maxSlotNumber);
//    }
//
//    @Test
//    void testFindTop1StartTimeByCurrentDate() {
//        LocalDate currentDate = LocalDate.of(2024, 5, 15);
//        LocalTime startTime = slotRepository.findTop1StartTimeByCurrentDate(currentDate);
//        assertNotNull(startTime);
//        assertEquals(LocalTime.of(10, 0), startTime);
//    }
//}
