package com.bet.BettingGame.service;

import com.bet.BettingGame.model.Bet;
import com.bet.BettingGame.model.BetResultDTO;
import com.bet.BettingGame.repository.BetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
public class BetServiceImp implements BetService{

    @Autowired
    BetRepository betRepository;
    @Override
    public Bet saveBet(Bet bet) {
        try {

        this.betRepository.save(bet);
        return bet;

        } catch (Exception e) {
            log.error("An error occurred while saving bet: " + e.getMessage());
            // Optionally, you can rethrow the exception or return null
            throw new RuntimeException("Failed to save bet", e);
        }
    }



public Long findHorseIdWithMaxBets(LocalDateTime startDateTime, LocalDateTime endDateTime, int slotNumber, LocalDate currentDate, LocalDateTime currentTime) {

    try {
        log.info("BetServiceImp  current time....." + currentTime);
//    LocalDate  currentDate = LocalDate.now();
        log.info("BetServiceImp " + currentDate);
//        List<Long> result = betRepository.findHorseIdWithMaxBets(currentTime,slotNumber,currentDate);
        List<Long> result = betRepository.findHorseIdWithMaxBets(startDateTime, endDateTime, slotNumber, currentDate, currentTime);
//        Long result = betRepository.findHorseIdWithMaxBets(endTime,slotNumber);
        log.info("Result " + result);
        return result.isEmpty() ? null : result.get(0);
//        if (!result.isEmpty()) {
//
//            return result.get(0);
//
//        }
//        return null;
    } catch (Exception e) {
            log.error("An error occurred while finding horse ID with max bets: " + e.getMessage());
            // Optionally, you can rethrow the exception or return null
            throw new RuntimeException("Failed to find horse ID with max bets", e);
        }
    }

    @Override
    public List<Bet> findBetsByHorseIdAndSlotTime(Long winningHorseId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        try {
            return betRepository.findBetsByHorseIdAndSlotTime(winningHorseId, startDateTime, endDateTime);
        } catch (Exception e) {
            // You can log the exception for debugging purposes
            e.printStackTrace(); // or use a logger to log the exception

            // Optionally, you can throw a custom exception or handle the error gracefully
            throw new RuntimeException("Error occurred while fetching bets by horseId and slot time", e);
        }
    }


//    @Override
//    public List<BetResultDTO> findLast5BetResults(int pageNumber, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        return betRepository.findLast5BetResults(pageable);
//    }


    public LocalDateTime findLoginTimeByUserId(Long userid) {
        try {
        return betRepository.findLoginTimeByUserId(userid);
        } catch (Exception e) {
            log.error("An error occurred while finding login time by user ID: " + e.getMessage());
            // Optionally, you can rethrow the exception or return null
            throw new RuntimeException("Failed to find login time by user ID", e);
        }
    }

//    public List<Long> getWinningHorseIds(int slotNumber) {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime startTime = now.withNano((now.getNano() / 1_000_000) * 1_000_000);
//        // Implement the logic to retrieve all winning horse IDs for the given slot number
//        return betRepository.findWinningHorseIds(startTime,slotNumber);
//    }
}
