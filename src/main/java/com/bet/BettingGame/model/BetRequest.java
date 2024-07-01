package com.bet.BettingGame.model;

public class BetRequest {



    private  int userid;

    private Long selectedHorse;

    private float betAmount;

    private int slotNumber;
    private Long gameId;



    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }
    public float getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(float betAmount) {
        this.betAmount = betAmount;
    }

    public Long getSelectedHorse() {
        return selectedHorse;
    }

    public void setSelectedHorse(Long selectedHorse) {
        this.selectedHorse = selectedHorse;
    }



    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }



}
