package com.bet.BettingGame.model;

public class BetItemDTO {

    private Long betItemId;
    private Long gameId;
    private String betItemName;



    public Long getBetItemId() {
        return betItemId;
    }

    public void setBetItemId(Long betItemId) {
        this.betItemId = betItemId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getBetItemName() {
        return betItemName;
    }

    public void setBetItemName(String betItemName) {
        this.betItemName = betItemName;
    }



}
