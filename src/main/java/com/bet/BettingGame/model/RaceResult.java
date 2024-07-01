package com.bet.BettingGame.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="race_result")
public class RaceResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slot_number")
    private int slotNumber;
    @Column(name="winning_horse_index")
    private Long winningHorseIndex;

    @Column(name="bet_time")
    private LocalDateTime betTime;
    @Column(name = "userid")
    private int userid;
    @Column(name = "slot_id")
    private Long slotId;
    @Column(name = "Username")
    private String Username;

    @Column(name = "bet_item_name")
    private String betItemName;

    @Column(name = "game_name")
    private String gameName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "UserID", insertable = false, updatable = false)
    private UserDetails userDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winning_horse_index", referencedColumnName = "bet_item_id", insertable = false, updatable = false)
    private BetItem betItem;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_id", referencedColumnName = "slot_id", insertable = false, updatable = false)
    private SlotDetails slotDetails;
    private Float winningPrice;

    private boolean winningPriceAwarded;





    public boolean isWinningPriceAwarded() {
        return winningPriceAwarded;
    }

    public void setWinningPriceAwarded(boolean winningPriceAwarded) {
        this.winningPriceAwarded = winningPriceAwarded;
    }

    public Float getWinningPrice() {
        return winningPrice;
    }

    public void setWinningPrice(Float winningPrice) {
        this.winningPrice = winningPrice;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }


    public String getBetItemName() {
        return betItemName;
    }

    public void setBetItemName(String betItemName) {
        this.betItemName = betItemName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public SlotDetails getSlotDetails() {
        return slotDetails;
    }

    public void setSlotDetails(SlotDetails slotDetails) {
        this.slotDetails = slotDetails;
    }


    public BetItem getBetItem() {
        return betItem;
    }

    public void setBetItem(BetItem betItem) {
        this.betItem = betItem;
    }


    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Long getWinningHorseIndex() {
        return winningHorseIndex;
    }

    public void setWinningHorseIndex(Long winningHorseIndex) {
        this.winningHorseIndex = winningHorseIndex;
    }

   // Index of the winning horse

    public LocalDateTime getBetTime() {
        return betTime;
    }

    public void setBetTime(LocalDateTime betTime) {
        this.betTime = betTime;
    }


    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }



    // Other fields, constructors, getters, and setters
}

