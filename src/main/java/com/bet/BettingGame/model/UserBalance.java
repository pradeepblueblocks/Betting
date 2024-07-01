package com.bet.BettingGame.model;

import javax.persistence.*;

@Entity
@Table(name="user_balance")
public class UserBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;
    @Column(name="user_id")
    private int userId;

    @Column(name="wallet_balance")
    private float walletBalance;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id", referencedColumnName ="UserID", insertable = false, updatable = false)
    private UserDetails userDetails;

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


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public float getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(float walletBalance) {
        this.walletBalance = walletBalance;
    }


}
