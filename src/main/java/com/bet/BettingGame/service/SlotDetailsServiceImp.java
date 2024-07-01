package com.bet.BettingGame.service;

import com.bet.BettingGame.model.SlotDetails;
import com.bet.BettingGame.repository.SlotDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SlotDetailsServiceImp implements SlotDetailsService {

    @Autowired
    private SlotDetailsRepository slotDetailsRepository;

    @Transactional
    public void deleteBySlotAdminIdAndSelectedDate(Long slotAdminId, LocalDate selectedDate) {
//    public void deleteByAdminAndSelectedDate(Long admin, LocalDate selectedDate) {
        try {
            slotDetailsRepository.deleteBySlotAdminIdAndSelectedDate(slotAdminId, selectedDate);
        } catch (Exception e) {
            // Log the error and handle it according to your application's requirements
            e.printStackTrace();
            throw new RuntimeException("Failed to delete slots by admin and selected date: " + e.getMessage(), e);
        }
    }

    @Override
    public List<SlotDetails> findSlotsByNumber(int slotNumber) {
        try {
            return slotDetailsRepository.findBySlotNumber(slotNumber);
        } catch (Exception e) {
            log.error("An error occurred while finding slots by number: " + e.getMessage());
            throw new RuntimeException("Failed to find slots by number", e);
        }
    }

    @Override
    public Optional<SlotDetails> findById(Long slotId) {
        try {
            return slotDetailsRepository.findById(slotId);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace(); // or use a logger to log the exception

            // Optionally, throw a custom exception or handle the error gracefully
            throw new RuntimeException("Error occurred while fetching SlotDetails by ID: " + slotId, e);
        }
    }

    public Integer findMaxSlotNumberByAdmin(int admin) {
        try {
            return slotDetailsRepository.findMaxSlotNumberByAdmin(admin);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace(); // or use a logger to log the exception

            // Optionally, throw a custom exception or handle the error gracefully
            throw new RuntimeException("Error occurred while fetching max slot number for admin: " + admin, e);
        }
    }

//    @Override
//    public LocalTime findTop1StartTimeByCurrentDate(LocalDate currentDate) {
//        try {
//            return slotDetailsRepository.findTop1StartTimeByCurrentDate(currentDate);
//        } catch (Exception e) {
//            // Log the exception for debugging purposes
//            e.printStackTrace(); // or use a logger to log the exception
//
//            // Optionally, throw a custom exception or handle the error gracefully
//            throw new RuntimeException("Error occurred while fetching top 1 start time for current date: " + currentDate, e);
//        }
//    }
//





//    @Override
//    public SlotDetails saveSlot(SlotDetails slot) {
//        try {
//       this.slotRepository.save(slot);
//        return slot;
//        } catch (Exception e) {
//            // Log the error and handle it according to your application's requirements
//            e.printStackTrace();
//            throw new RuntimeException("Failed to save slot details: " + e.getMessage(), e);
//        }
//    }


//    @Override
//    public SlotDetails getSlotByStartTime(LocalTime startTime) {
//        try {
//        // Implement the logic to retrieve the slot from the database by start time
//        return slotRepository.findByStartTime(startTime);
//        } catch (Exception e) {
//            // Log the error and handle it according to your application's requirements
//            e.printStackTrace();
//            throw new RuntimeException("Failed to retrieve slot details by start time: " + e.getMessage(), e);
//        }
//    }

//    public void deleteSlot(Long slotId) {
//        try {
//        slotRepository.deleteById(slotId);
//        } catch (Exception e) {
//            // Log the error and handle it according to your application's requirements
//            e.printStackTrace();
//            throw new RuntimeException("Failed to delete slot by ID: " + e.getMessage(), e);
//        }
//    }
}
