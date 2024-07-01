package com.bet.BettingGame.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class RaceResultDTO {



    private Long winningHorseIndex;
    private String winningItemName;
    private LocalDateTime betTime;
    private int slotNumber;
    private String username;
    private String gameName;
//    private int userid;




//    public RaceResultDTO(Long winningHorseIndex,LocalDateTime betTime, int slotNumber, Long userid) {
//        this.slotNumber = slotNumber;
//        this.winningHorseIndex = winningHorseIndex;
//        this.userid = userid;
//        this.betTime = betTime;
//    }


}
