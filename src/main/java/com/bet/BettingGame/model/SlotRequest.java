package com.bet.BettingGame.model;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class SlotRequest {


    private LocalTime adminStartTime;
    private LocalTime adminEndTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate selectedDate;

    private int adminId;
    private int slotDurationInMinutes;
    private Long gameId;
    private Long slotId;
    private float winningPrice;




    public float getWinningPrice() {
        return winningPrice;
    }

    public void setWinningPrice(float winningPrice) {
        this.winningPrice = winningPrice;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }





    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }



    // Constructors, getters, setters, etc.
    // ...



    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }



    public int getSlotDurationInMinutes() {
        return slotDurationInMinutes;
    }

    public void setSlotDurationInMinutes(int slotDurationInMinutes) {
        this.slotDurationInMinutes = slotDurationInMinutes;
    }
}
