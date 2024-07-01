package com.bet.BettingGame.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="slot_details")
public class SlotDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slot_id")
    private Long slotId;

    @Column(name="total_slots")
    private int totalSlots;
    @Column(name="slot_number")
    private int  slotNumber;

    @Column(name="start_time")
    private LocalTime startTime;

    @Column(name="end_time")
    private LocalTime endTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="selected_date")
    private LocalDate selectedDate;

//    private int slotDurationInSeconds;



    @Column(name="admin_id")
    private int admin;

    @Column(name="game_id")
    private Long gameId;

    @Column(name="slot_admin_id")
    private Long slotAdminId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", referencedColumnName = "game_id", insertable = false, updatable = false)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_admin_id",referencedColumnName = "slot_admin_id",insertable = false,updatable = false)
    private Slot slot;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id",referencedColumnName = "Admin_id",insertable = false,updatable = false)
    private AdminDetails adminDetails;

    @Column(name = "winning_price")
    private float winningPrice;


    public float getWinningPrice() {
        return winningPrice;
    }

    public void setWinningPrice(float winningPrice) {
        this.winningPrice = winningPrice;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
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



    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }



    public AdminDetails getAdminDetails() {
        return adminDetails;
    }

    public void setAdminDetails(AdminDetails adminDetails) {
        this.adminDetails = adminDetails;
    }



//    @ManyToOne
//    @JoinColumn(name = "admin_id", referencedColumnName = "id")

//    private Long adminId;



//    public int getSlotDurationInSeconds() {
//        return slotDurationInSeconds;
//    }
//
//    public void setSlotDurationInSeconds(int slotDurationInSeconds) {
//        this.slotDurationInSeconds = slotDurationInSeconds;
//    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }




    //    private int timeLimitInSeconds;
//    private int remainingSlots;


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


    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }








    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }


//    public Slot(int totalSlots, LocalTime endTime, String selectedDate, LocalTime startTime) {
//        this.endTime=endTime;
//        this.selectedDate=selectedDate;
//        this.startTime=startTime;


//        this.totalSlots=totalSlots;
//
//
//    }


    @Override
    public String toString() {
        return "SlotDetails{" +
                "slotId=" + slotId+
                ", totalSlots=" + totalSlots +
                ", slotNumber=" + slotNumber +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", selectedDate=" + selectedDate +
                ", admin=" + admin +

                '}';
    }

    public SlotDetails(Long slotId, int totalSlots, int slotNumber, LocalTime startTime, LocalTime endTime, LocalDate selectedDate, int admin) {
        this.slotId = slotId;
        this.totalSlots = totalSlots;
        this.slotNumber = slotNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.selectedDate = selectedDate;
        this.admin = admin;

    }
//    public SlotDetails(Long slotId, int totalSlots, LocalTime startTime, LocalTime endTime, LocalDate selectedDate, Admin admin) {
//        this.slotId = slotId;
//        this.totalSlots = totalSlots;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.selectedDate = selectedDate;
//        this.admin = admin;
//    }

    public SlotDetails() {
    }
}