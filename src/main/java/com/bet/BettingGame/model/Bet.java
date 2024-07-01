package com.bet.BettingGame.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="bet")
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bet_id")
    private Long betId;

    @Column(name="slot_number")
    private int slotNumber;
    @Column(name="slot_id")
    private Long slotId;
    @Column(name="bet_amount")
    private float betAmount;



    //    @Column(name = "horse_id") // Map to the foreign key column
//    private Long horseId;
    @Column(name = "userid")
    private int userid;
    private LocalDateTime login_time;

    @Column(name="bet_item_id")
    private Long betItemId;

    @Column(name="game_id")
    private Long gameId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_id", referencedColumnName = "slot_id", insertable = false, updatable = false)
    private SlotDetails slot;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", referencedColumnName = "game_id", insertable = false, updatable = false)
    private Game game;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bet_item_id", referencedColumnName = "bet_item_id", insertable = false, updatable = false)
    private BetItem betItem;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "UserID", insertable = false, updatable = false)
    private UserDetails userDetails;

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public BetItem getBetItem() {
        return betItem;
    }

    public void setBetItem(BetItem betItem) {
        this.betItem = betItem;
    }
    public float getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(float betAmount) {
        this.betAmount = betAmount;
    }


    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
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

    public SlotDetails getSlot() {
        return slot;
    }

    public void setSlot(SlotDetails slot) {
        this.slot = slot;
    }


    public LocalDateTime getLogin_time() {
        return login_time;
    }

    public void setLogin_time(LocalDateTime login_time) {
        this.login_time = login_time;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "betId=" + betId +
                ", slotNumber=" + slotNumber +
                ", slotId=" + slotId +
                ", betAmount=" + betAmount +
                ", userid=" + userid +
                ", login_time=" + login_time +
                ", betItemId=" + betItemId +
                ", gameId=" + gameId +
                ", game=" + game +
                ", betItem=" + betItem +
                '}';
    }


//    @ManyToOne
//    @JoinColumn(name="horseId")
//    private Horse horseId;


//    public Long getHorseId() {
//        return horseId;
//    }
//
//    public void setHorseId(Long horseId) {
//        this.horseId = horseId;
//    }




//    @ManyToOne
//    @JoinColumn(name = "horse_id") // Specify the foreign key column
//    private Horse horse;



//    @ManyToOne
//    @JoinColumn(name = "userid")















}
