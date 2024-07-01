package com.bet.BettingGame.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="slot")
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="slot_admin_id")
    private Long slotAdminId;

    @Column(name="game_id")
    private Long gameId;


    @Column(name="slot_duration_in_seconds")
    private  int slotDurationInSeconds;

    @Column(name = "admin_start_time")
    private LocalTime adminStartTime;

    @Column(name ="admin_end_time")
    private LocalTime adminEndTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", referencedColumnName = "game_id", insertable = false, updatable = false)
    private Game game;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "selected_date")
    private LocalDate selectedDate;

    @Column(name = "winning_price")
    private float winningPrice;




    public float getWinningPrice() {
        return winningPrice;
    }

    public void setWinningPrice(float winningPrice) {
        this.winningPrice = winningPrice;
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    public Long getSlotAdminId() {
        return slotAdminId;
    }

    public void setSlotAdminId(Long slotAdminId) {
        this.slotAdminId = slotAdminId;
    }


    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public int getSlotDurationInSeconds() {
        return slotDurationInSeconds;
    }

    public void setSlotDurationInSeconds(int slotDurationInSeconds) {
        this.slotDurationInSeconds = slotDurationInSeconds;
    }

    public LocalTime getAdminStartTime() {
        return adminStartTime;
    }

    public void setAdminStartTime(LocalTime adminStartTime) {
        this.adminStartTime = adminStartTime;
    }

    public LocalTime getAdminEndTime() {
        return adminEndTime;
    }

    public void setAdminEndTime(LocalTime adminEndTime) {
        this.adminEndTime = adminEndTime;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
