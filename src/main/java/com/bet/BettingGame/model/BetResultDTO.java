package com.bet.BettingGame.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BetResultDTO {

    private Long betItemId;
    private LocalDateTime login_time;
    private int slotNumber;
    private int userid;



//    public BetResultDTO(Long userid, float betAmount, Long horseId, LocalDateTime login_time, int slotNumber) {
//        this.userid = userid;
//        this.betAmount = betAmount;
//        this.horseId = horseId;
//        this.login_time = login_time;
//        this.slotNumber = slotNumber;
//
//    }


}
