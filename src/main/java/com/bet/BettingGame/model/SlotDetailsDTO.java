package com.bet.BettingGame.model;

import java.time.LocalTime;

public class SlotDetailsDTO {


    private Long slotId;
    private LocalTime startTime;

    private LocalTime endTime;
    private  int slotNumber;


    public SlotDetailsDTO(Long slotId, LocalTime startTime, LocalTime endTime, int slotNumber) {
        this.slotId = slotId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotNumber = slotNumber;

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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }


}
