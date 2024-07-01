package com.bet.BettingGame.model;

public class BetItemRequest {
    private Long gameId;
    private String betItemName;




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
