package com.bet.BettingGame.service;

import com.bet.BettingGame.model.Bet;
import com.bet.BettingGame.model.BetResultDTO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface BetService {
    Bet saveBet(Bet bet);

//    Long getHorseIdWithMaxBets(LocalDateTime currentTime, int slotNumber);

//Long getHorseIdWithMaxBets(LocalDateTime endTime,int slotNumber);
    LocalDateTime findLoginTimeByUserId(Long userid);

//    List<Long> getWinningHorseIds(int slotNumber);

    Long findHorseIdWithMaxBets(LocalDateTime startDateTime, LocalDateTime endDateTime, int slotNumber, LocalDate currentDate, LocalDateTime currentTime);


    List<Bet> findBetsByHorseIdAndSlotTime(Long winningHorseId, LocalDateTime startDateTime, LocalDateTime endDateTime);

//    List<BetResultDTO> findLast5BetResults(int pageNumber, int pageSize);
}
