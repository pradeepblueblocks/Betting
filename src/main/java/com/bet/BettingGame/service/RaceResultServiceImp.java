package com.bet.BettingGame.service;

import com.bet.BettingGame.model.RaceResult;
import com.bet.BettingGame.model.RaceResultDTO;
import com.bet.BettingGame.repository.RaceResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RaceResultServiceImp implements RaceResultService {

    @Autowired
    private RaceResultRepository raceResultRepository;



    @Transactional
    public void updateOrSaveRaceResult(RaceResult raceResult,LocalDateTime endTime) {
        // Check if a RaceResult record with the same slotId and slotNumber exists
        try {
        if (LocalDateTime.now().isAfter(endTime)) {

            Optional<RaceResult> existingPreviousRaceResultOptional = raceResultRepository.findBySlotIdAndSlotNumberAndUserid(raceResult.getSlotId(), raceResult.getSlotNumber() - 1, raceResult.getUserid());
            Optional<RaceResult> existingCurrentRaceResultOptional = raceResultRepository.findBySlotIdAndSlotNumberAndUserid(raceResult.getSlotId(), raceResult.getSlotNumber(), raceResult.getUserid());
//            Optional<RaceResult> existingRaceResultOptional = raceResultRepository.findBySlotIdAndSlotNumberAndUserid(raceResult.getSlotId(), raceResult.getSlotNumber(),raceResult.getUserid());

            if (existingPreviousRaceResultOptional.isPresent()) {
                RaceResult existingPreviousRaceResult = existingPreviousRaceResultOptional.get();
                existingPreviousRaceResult.setBetTime(raceResult.getBetTime());
                existingPreviousRaceResult.setWinningHorseIndex(raceResult.getWinningHorseIndex());
                existingPreviousRaceResult.setUserid(raceResult.getUserid());
                // Update other fields as needed
                raceResultRepository.save(existingPreviousRaceResult);
            }

            // Save for the current slot number if no existing record is found
            if (!existingCurrentRaceResultOptional.isPresent()) {
                raceResultRepository.save(raceResult);
            }
        }
        } catch (Exception e) {
            // Log the error and handle it according to your application's requirements
            e.printStackTrace();
            throw new RuntimeException("Failed to update or save race result: " + e.getMessage(), e);
        }
    }

    @Override
    public Long getWinningHorseIdBySlotNumber(int slotNumber) {
        try {
            return raceResultRepository.findWinningHorseIdBySlotNumber(slotNumber);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace(); // or use a logger to log the exception

            // Optionally, you can throw a custom exception or handle the error gracefully
            throw new RuntimeException("Error occurred while fetching winning horse ID by slot number: " + slotNumber, e);
        }
    }



    public void save(RaceResult raceResult) {
        raceResultRepository.save(raceResult);
    }

    public Optional<RaceResult> findBySlotIdAndSlotNumberAndUserId(Long slotId, int slotNumber, int userId) {
        return raceResultRepository.findBySlotIdAndSlotNumberAndUserid(slotId, slotNumber, userId);
    }

//        Optional<RaceResult> existingRaceResultOptional = raceResultRepository.findBySlotIdAndSlotNumberAndMinuteDifference(raceResult.getSlotId(),raceResult.getSlotNumber());
//            if (existingRaceResultOptional.isPresent()) {
//                // If a record exists, update it with the new data
//                RaceResult existingRaceResult = existingRaceResultOptional.get();
//                existingRaceResult.setBetTime(raceResult.getBetTime());
//                existingRaceResult.setWinningHorseIndex(raceResult.getWinningHorseIndex());
//                existingRaceResult.setUserid(raceResult.getUserid());
//                // Update other fields as needed
//                raceResultRepository.save(existingRaceResult);
//            } else {
//                // If no record exists, save the new data as a new RaceResult record
//                raceResultRepository.save(raceResult);
//            }
//
//        }
//    }

//    @Override
//    public Long findHorseWithMaxBets(int slotNumber) {
//        return raceResultRepository.findHorseWithMaxBets(slotNumber);
//    }
}
