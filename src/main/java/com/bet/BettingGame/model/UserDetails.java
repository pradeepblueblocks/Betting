package com.bet.BettingGame.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "UserDetails")
public class UserDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="UserID")
    private int UserID;
    @Column(name="Username")
    private String Username;
    @Column(name ="Password")
    private String Password;

    @Column(name="Email")
    private String Email;
    @Column(name="DateOfBirth")
    private Date DateOfBirth;
    @Column(name="Country")
    private String Country;

    @Column(name ="Phonenumber")
    private String Phonenumber;
    @Column(name ="Name")
    private String Name;


    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }



}
