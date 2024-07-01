package com.bet.BettingGame.service;


import com.bet.BettingGame.model.Slot;
import com.bet.BettingGame.repository.SlotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
public class SlotServiceImp implements SlotService{

    @Autowired
    private SlotRepository slotRepository;

    @Override
    public Slot saveSlot(Slot slot) {
        try {

            this.slotRepository.save(slot);
            return slot;

        } catch (Exception e) {
            log.error("An error occurred while saving slot: " + e.getMessage());
            // Optionally, you can rethrow the exception or return null
            throw new RuntimeException("Failed to save slots", e);
        }
    }


    public Long findIdByAdminStartTimeAndAdminEndTimeAndGameId(LocalTime adminStartTime,LocalTime adminEndTime,Long gameId,LocalDate selectedDate){
        Time startTime = Time.valueOf(adminStartTime);
        Time endTime = Time.valueOf(adminEndTime);

//        Date sqlDate = Date.valueOf(selectedDate);

        return slotRepository.getSlotAdminIdByTime(startTime, endTime, gameId,selectedDate);
//        return slotRepository.findIdByAdminStartTimeAndAdminEndTime(startTime,endTime);
    }

    public boolean checkForSlotOverlap(Long gameId, LocalDate selectedDate, LocalTime adminStartTime, LocalTime adminEndTime) {
        // Convert LocalDate and LocalTime to java.sql.Date and java.sql.Time
        Date sqlDate = Date.valueOf(selectedDate);
        Time startTime = Time.valueOf(adminStartTime);
        Time endTime = Time.valueOf(adminEndTime);

        // Call the repository method with the converted parameters
        List<Slot> overlappingSlots = slotRepository.findByGameIdAndSelectedDateAndAdminTimeRange(
                gameId,
                sqlDate,
                startTime,
                endTime
        );

        // Check if there are any overlapping slots
        return !overlappingSlots.isEmpty();
    }

}
