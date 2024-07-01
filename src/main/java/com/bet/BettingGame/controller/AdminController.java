package com.bet.BettingGame.controller;


import com.bet.BettingGame.model.*;
import com.bet.BettingGame.repository.SlotDetailsRepository;
//import com.bet.BettingGame.service.AdminService;
import com.bet.BettingGame.repository.SlotRepository;
import com.bet.BettingGame.service.SlotDetailsService;

import com.bet.BettingGame.service.SlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    private SlotDetailsService slotDetailsService;
    @Autowired
    private SlotService slotService;
    @Autowired
    private SlotDetailsRepository slotDetailsRepository;
    @Autowired
    private SlotRepository slotRepository;


    @PostMapping("/slots")
    public ResponseEntity<String> createSlot(@RequestBody SlotRequest request) {
        try {
            LocalTime currentTime = LocalTime.now();
            log.info("CurrentTime in /slots postmapping: {}", currentTime);

            int slotDuration = request.getSlotDurationInMinutes();
            LocalTime adminStartTimeRequest = request.getAdminStartTime();
            LocalTime adminEndTimeRequest = request.getAdminEndTime();
            Long gameId = request.getGameId();
            Integer adminId = request.getAdminId();
            LocalDate selectedDate = request.getSelectedDate();
            Float winningPrice  = request.getWinningPrice();

            log.info("slotDuration: {}", slotDuration);
            log.info("REQUEST START TIME: {} AND ENDTIME: {}", adminStartTimeRequest, adminEndTimeRequest);
            log.info("GameId: {}", gameId);
            log.info("WinningPrice: {}",winningPrice);

            int slotDurationInSeconds = slotDuration * 60;

            // Check for invalid times
            if (adminStartTimeRequest.equals(adminEndTimeRequest)) {
                return ResponseEntity.badRequest().body("Start Time and End Time should not be the same");
            }

            // Calculate total duration and validate slot duration
            Duration totalDuration = Duration.between(adminStartTimeRequest, adminEndTimeRequest);
            long totalSeconds = totalDuration.getSeconds();
            if (slotDurationInSeconds > totalSeconds) {
                return ResponseEntity.badRequest().body("Slot duration should be less than the duration between start time and end time");
            }

            // Validate adminId existence
            if (adminId == null) {
                return ResponseEntity.badRequest().body("Admin ID is required");
            }

            // Check for overlap in the slot table
            if (slotService.checkForSlotOverlap(gameId, selectedDate, adminStartTimeRequest, adminEndTimeRequest)) {
                return ResponseEntity.badRequest().body("Slot overlaps with existing slots. Cannot create.");
            }

            // Create and save the main slot entry
            Slot slot = new Slot();
            slot.setSlotDurationInSeconds(slotDurationInSeconds);
            slot.setGameId(gameId);
            slot.setAdminStartTime(adminStartTimeRequest);
            slot.setAdminEndTime(adminEndTimeRequest);
            slot.setSelectedDate(selectedDate);
            slot.setWinningPrice(winningPrice);
            slotService.saveSlot(slot);

            // Calculate total number of slots
            int totalSlots = (int) (totalSeconds / slotDurationInSeconds);
            log.info("Total slots: {}", totalSlots);

            // Fetch the current highest slot number from the database
            Integer currentMaxSlotNumber = slotDetailsService.findMaxSlotNumberByAdmin(adminId);
            int nextSlotNumber = (currentMaxSlotNumber != null ? currentMaxSlotNumber : 0) + 1;
            log.info("Next slot number: {}", nextSlotNumber);

            // Find slotAdminId
            Long slotAdminId = slotService.findIdByAdminStartTimeAndAdminEndTimeAndGameId(adminStartTimeRequest, adminEndTimeRequest, gameId, selectedDate);
            log.info("AdminSlotId: {}", slotAdminId);

            // Create new slots
            List<SlotDetails> newSlots = new ArrayList<>();
            for (int i = 0; i < totalSlots; i++) {
                LocalTime endTime = adminStartTimeRequest.plusSeconds(slotDurationInSeconds - 1);

                SlotDetails slotDetails = new SlotDetails();
                slotDetails.setAdmin(adminId);
                slotDetails.setSelectedDate(selectedDate);
                slotDetails.setSlotNumber(nextSlotNumber++);
                slotDetails.setStartTime(adminStartTimeRequest);
                slotDetails.setEndTime(endTime);
                slotDetails.setTotalSlots(totalSlots);
                slotDetails.setGameId(gameId);
                slotDetails.setSlotAdminId(slotAdminId);
                slotDetails.setWinningPrice(winningPrice);

                newSlots.add(slotDetails);
                adminStartTimeRequest = endTime.plusSeconds(1);
            }

            // Save new slots
            slotDetailsRepository.saveAll(newSlots);
            return ResponseEntity.ok("Slots created successfully");

        } catch (Exception e) {
            log.error("An error occurred while creating slots: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request");
        }
    }



        @GetMapping("/adminUpcomingSlots")
        public ResponseEntity<Map<String, Object>> getUpcomingSlots(HttpSession session) {
            log.info("Entered into GetMapping adminUpcomingSlots Method...");

            try {
                LocalDateTime currentDateTime = LocalDateTime.now();
                LocalTime currentTime = currentDateTime.toLocalTime();
                LocalDate currentDate = LocalDate.now();

                // Retrieve upcoming slots using the stored procedure
                List<Object[]> upcomingSlots= slotRepository.findAdminSlotsForCurrentDate(currentDate, currentTime);
                log.info("Upcoming Slots: {}", upcomingSlots);



                // Map the results to SlotDTO
                List<SlotDTO> slotDTOs = upcomingSlots.stream()
                        .map(obj -> new SlotDTO(
                                ((Number) obj[0]).longValue(),        // slotAdminId
                                ((Time) obj[1]).toLocalTime(),       // adminStartTime
                                ((Time) obj[2]).toLocalTime(),       // adminEndTime
                                ((Number) obj[3]).intValue(),        // slotDurationInSeconds
                                ((Number) obj[4]).longValue(),       // gameId
                                (String) obj[5]                      // gameName
                        ))
                        .collect(Collectors.toList());


                Map<String, Object> response = new HashMap<>();

                response.put("slots", slotDTOs);

                return ResponseEntity.ok(response);
            } catch (Exception e) {
                log.error("An error occurred in GetMapping AdminUpcomingSlot Method: " + e.getMessage(), e);

                // Return an appropriate error response
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", "An error occurred while fetching slots from slot Table.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
            }
        }



//////////modified on 3/6/2024//////////////////////

@GetMapping("/getSlot/{slotadminid}")
public ResponseEntity<Map<String, Object>> getSlotDetails(@PathVariable Long slotadminid) {
    Map<String, Object> errorResponse = null;
    try {
        errorResponse = new HashMap<>();
        errorResponse.put("error", "SlotAdminId not found");
        Optional<Slot> slotOptional = slotRepository.findById(slotadminid);
        if (!slotOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body( errorResponse);
        }

        Slot slot = slotOptional.get();
        SlotDTO slotDTO = new SlotDTO(
                slot.getSlotAdminId(),
                slot.getAdminStartTime(),
                slot.getAdminEndTime(),
                slot.getSlotDurationInSeconds(),
                slot.getGameId(),
                slot.getGame().getGameName()// Assuming `Game` has a getId() method
        );
        Map<String, Object> response = new HashMap<>();
        response.put("slot",slotDTO);
        return ResponseEntity.ok(response);
    } catch (Exception e) {
        e.printStackTrace();

        errorResponse.put("error", "An error occurred while fetching slot from slot Table.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}







    @PutMapping("/updateSlot/{slotadminid}")
    public ResponseEntity<String> updateSlot(@PathVariable Long slotadminid, @RequestBody SlotRequest slotRequest) {
        try {
            Optional<Slot> slotOptional = slotRepository.findById(slotadminid);
            if (!slotOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Slot not found");
            }

            Slot existingSlot = slotOptional.get();
            Long existingSlotGameId = existingSlot.getGameId();
            LocalTime existingSlotAdminStartTime = existingSlot.getAdminStartTime();
            LocalTime existingSlotAdminEndTime = existingSlot.getAdminEndTime();
            LocalDate existingSlotSelectedDate = existingSlot.getSelectedDate();
            Long existingSlotAdminId = existingSlot.getSlotAdminId();
            LocalTime currentTime = LocalTime.now();

            log.info("ExistingSlotAdminID: " + existingSlotAdminId);

            Integer adminId = slotRequest.getAdminId();
            LocalTime slotRequestStartTime = slotRequest.getAdminStartTime();
            LocalTime slotRequestEndTime = slotRequest.getAdminEndTime();
            Integer slotRequestDurationInMinutes = slotRequest.getSlotDurationInMinutes();

            // Check if current time is before adminStartTime
            if (currentTime.isAfter(existingSlotAdminStartTime)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot update slot after its start time");
            }

            // Check for overlapping slots if adminStartTime or adminEndTime are being updated
            if (slotRequestStartTime != null || slotRequestEndTime != null) {
                Time newAdminStartTime = Time.valueOf(slotRequestStartTime != null ? slotRequestStartTime : existingSlotAdminStartTime);
                Time newAdminEndTime = Time.valueOf(slotRequestEndTime != null ? slotRequestEndTime : existingSlotAdminEndTime);

                // Check for overlapping
                List<Slot> overlappingSlots = slotRepository.findByGameIdAndSelectedDateAndAdminTimeRange(
                        existingSlotGameId,
                        Date.valueOf(existingSlotSelectedDate),
                        newAdminStartTime,
                        newAdminEndTime
                );
                // Exclude the current slot from the overlap check
                overlappingSlots.remove(existingSlot);

                if (!overlappingSlots.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("New time range overlaps with existing slots");
                }

                // Update adminStartTime and adminEndTime if provided
                if (slotRequestStartTime != null) {
                    existingSlot.setAdminStartTime(slotRequestStartTime);
                }
                if (slotRequestEndTime != null) {
                    existingSlot.setAdminEndTime(slotRequestEndTime);
                }
            }

            // Update slotDurationInSeconds if provided
            if (slotRequestDurationInMinutes != null && slotRequestDurationInMinutes > 0) {
                existingSlot.setSlotDurationInSeconds(slotRequestDurationInMinutes * 60);
            }

            slotRepository.save(existingSlot);

            int slotDurationInSeconds = (slotRequestDurationInMinutes != null ? slotRequestDurationInMinutes : 0) * 60;
            // Calculate total duration and validate slot duration
            Duration totalDuration = Duration.between(slotRequestStartTime, slotRequestEndTime);
            log.info("Total Duration: {}", totalDuration);
            long totalSeconds = totalDuration.getSeconds();
            log.info("totalSeconds: {}", totalSeconds);

            if (slotDurationInSeconds > totalSeconds) {
                return ResponseEntity.badRequest().body("Slot duration should be less than the duration between start time and end time");
            }

            log.info("slotDurationInSeconds: {}", slotDurationInSeconds);

            // Validate adminId existence
            if (adminId == null) {
                return ResponseEntity.badRequest().body("Admin not found with ID: " + adminId);
            }

            // Calculate total number of slots
            int totalSlots = (int) (totalSeconds / slotDurationInSeconds);
            log.info("Total slots: {}", totalSlots);

            // Fetch the current highest slot number from the database
            Integer currentMaxSlotNumber = slotDetailsService.findMaxSlotNumberByAdmin(adminId);
            int nextSlotNumber = (currentMaxSlotNumber != null ? currentMaxSlotNumber : 0) + 1;
            log.info("Next slot number: {}", nextSlotNumber);

            slotDetailsService.deleteBySlotAdminIdAndSelectedDate(existingSlotAdminId, existingSlotSelectedDate);
            Long slotAdminId = slotService.findIdByAdminStartTimeAndAdminEndTimeAndGameId(slotRequestStartTime, slotRequestEndTime, existingSlotGameId,existingSlotSelectedDate);

            // Create new slots
            List<SlotDetails> newSlots = new ArrayList<>();
            for (int i = 0; i < totalSlots; i++) {
                LocalTime endTime = slotRequestStartTime.plusSeconds(slotDurationInSeconds - 1);

                SlotDetails slotDetails = new SlotDetails();
                slotDetails.setAdmin(adminId);
                slotDetails.setSelectedDate(existingSlotSelectedDate);
                slotDetails.setSlotNumber(nextSlotNumber++);
                slotDetails.setStartTime(slotRequestStartTime);
                slotDetails.setEndTime(endTime);
                slotDetails.setTotalSlots(totalSlots);
                slotDetails.setGameId(existingSlotGameId);

                // Find or calculate slotAdminId
                log.info("AdminSlotId: {}", slotAdminId);
                slotDetails.setSlotAdminId(slotAdminId);

                newSlots.add(slotDetails);
                slotRequestStartTime = endTime.plusSeconds(1);
            }

            // Save new slots
            slotDetailsRepository.saveAll(newSlots);

            return ResponseEntity.ok("Slot updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the slot");
        }
    }


    }








