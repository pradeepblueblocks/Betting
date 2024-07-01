package com.bet.BettingGame.model;

import java.time.LocalTime;

public class SlotDTO {
    private Long slotAdminId;
    private LocalTime adminStartTime;
    private LocalTime adminEndTime;
    private int slotDurationInMinutes;
    private Long gameId;
    private String gameName;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }



    public SlotDTO(Long slotAdminId, LocalTime adminStartTime, LocalTime adminEndTime, int slotDurationInSeconds ,Long gameId,String gameName) {
        this.slotAdminId = slotAdminId;
        this.adminStartTime = adminStartTime;
        this.adminEndTime = adminEndTime;
        this.slotDurationInMinutes = slotDurationInSeconds/60;
        this.gameId = gameId;
        this.gameName = gameName;
    }


    public Long getSlotAdminId() {
        return slotAdminId;
    }

    public void setSlotAdminId(Long slotAdminId) {
        this.slotAdminId = slotAdminId;
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

    public int getSlotDurationInMinutes() {
        return slotDurationInMinutes;
    }

    public void setSlotDurationInMinutes(int slotDurationInMinutes) {
        this.slotDurationInMinutes = slotDurationInMinutes;
    }
}
