package com.bet.BettingGame.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class BettingTransactionDTO {

    private Long transactionId;
    private float betAmount;
    private LocalDateTime betTime;
    private Long slotId;
    private int slotNumber;
    private  String userName;
    private String betItemName;
    private float winningPrice;
    private boolean winningPriceAwarded;
    private String gameName;
    private String ProfitorLoss;
    private float walletBalance;
}
