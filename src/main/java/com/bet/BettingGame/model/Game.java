package com.bet.BettingGame.model;


import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="game_id")
    private Long gameId;



    @Column(name="game_name",nullable = false)
    private String gameName;

//    @Lob
//    @Column(name = "picture", columnDefinition = "BLOB")
//    private byte[] picture;


    @Column(name="picture_file_path")
    private String pictureFilePath;

    @Column(name = "is_active")
    private int isActive;


    // Default constructor
    public Game() {
    }

    public Game(String gameName, String pictureFilePath) {
        this.gameName = gameName;
        this.pictureFilePath = pictureFilePath;

    }

//    public Game(String gameName, byte[] picture) {
//        this.gameName = gameName;
//        this.picture = picture;
//    }


    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getPictureFilePath() {
        return pictureFilePath;
    }

    public void setPictureFilePath(String pictureFilePath) {
        this.pictureFilePath = pictureFilePath;
    }


}