package com.bet.BettingGame.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
public class BettingTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private int userid;
    private float betAmount;
    private int slotNumber;
    private Long slotId;
    private Long gameId;
    private LocalDateTime betTime;
    private Long winningHorseIndex;
    private float winningPrice;
    private boolean winningPriceAwarded;
    private Long betId;
    private Long id;
    private float walletBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "betId", referencedColumnName = "bet_id", insertable = false, updatable = false)
    private Bet bet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private RaceResult raceResult;



    public float getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(float walletBalance) {
        this.walletBalance = walletBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RaceResult getRaceResult() {
        return raceResult;
    }

    public void setRaceResult(RaceResult raceResult) {
        this.raceResult = raceResult;
    }

    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public float getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(float betAmount) {
        this.betAmount = betAmount;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }


    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public LocalDateTime getBetTime() {
        return betTime;
    }

    public void setBetTime(LocalDateTime betTime) {
        this.betTime = betTime;
    }

    public Long getWinningHorseIndex() {
        return winningHorseIndex;
    }

    public void setWinningHorseIndex(Long winningHorseIndex) {
        this.winningHorseIndex = winningHorseIndex;
    }

    public float getWinningPrice() {
        return winningPrice;
    }

    public void setWinningPrice(float winningPrice) {
        this.winningPrice = winningPrice;
    }

    public boolean isWinningPriceAwarded() {
        return winningPriceAwarded;
    }

    public void setWinningPriceAwarded(boolean winningPriceAwarded) {
        this.winningPriceAwarded = winningPriceAwarded;
    }



}
