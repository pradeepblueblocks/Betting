package com.bet.BettingGame.service;

import com.bet.BettingGame.model.SlotDetails;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface SlotDetailsService {
//    List<Slot> getAllSlots();

//    SlotDetails saveSlot(SlotDetails slot);
////    SlotDetails getSlotByStartTime(LocalTime startTime);


//    void deleteByAdminAndSelectedDate(Admin admin, LocalDate selectedDate);
void deleteBySlotAdminIdAndSelectedDate(Long slotAdminId, LocalDate selectedDate);

    List<SlotDetails> findSlotsByNumber(int slotNumber);

    Optional<SlotDetails> findById(Long slotId);
    Integer findMaxSlotNumberByAdmin(int admin);
//    Integer findMaxSlotNumberByAdmin(Admin admin);
//    LocalTime findTop1StartTimeByCurrentDate(LocalDate currentDate);

//void deleteByAdminAndSelectedDate(Long admin, LocalDate selectedDate);
}
