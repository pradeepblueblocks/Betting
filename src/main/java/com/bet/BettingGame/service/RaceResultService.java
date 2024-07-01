package com.bet.BettingGame.service;

import com.bet.BettingGame.model.RaceResult;
import com.bet.BettingGame.model.RaceResultDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RaceResultService {



    void updateOrSaveRaceResult(RaceResult raceResult,LocalDateTime endTime);


    Long getWinningHorseIdBySlotNumber(int slotNumber);

    void save(RaceResult raceResult);

   Optional<RaceResult> findBySlotIdAndSlotNumberAndUserId(Long slotId, int slotNumber, int userId);
}
