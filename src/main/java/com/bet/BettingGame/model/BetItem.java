package com.bet.BettingGame.model;

import javax.persistence.*;

@Entity
@Table(name="bet_item")
public class BetItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bet_item_id")
    private Long betItemId;

    @Column(name="game_id")
    private  Long gameId;

    @Column(name = "bet_item_name")
    private String betItemName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", referencedColumnName = "game_id", insertable = false, updatable = false)
    private Game game;

//    public BetItem(Long betItemId, Long gameId, String betItemName, Game game) {
//        this.betItemId = betItemId;
//        this.gameId = gameId;
//        this.betItemName = betItemName;
//        this.game = game;
//    }

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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
