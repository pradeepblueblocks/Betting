package com.bet.BettingGame.service;

import com.bet.BettingGame.model.Slot;

import java.time.LocalDate;
import java.time.LocalTime;

public interface SlotService {
    Slot saveSlot(Slot slot);

    Long findIdByAdminStartTimeAndAdminEndTimeAndGameId(LocalTime adminStartTimeRequest,LocalTime  adminEndTimeRequest,Long gameId,LocalDate selectedDate);

    boolean checkForSlotOverlap(Long gameId, LocalDate selectedDate, LocalTime adminStartTimeRequest, LocalTime adminEndTimeRequest);
}
