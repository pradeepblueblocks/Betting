package com.bet.BettingGame.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="AdminDetails")
public class AdminDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Admin_id")
    private int Admin_id;
    @Column(name = "Username")
    private String Username;
    @Column(name="Password")
    private String Password;
    @Column(name="Email")
    private String Email;
    @Column(name="Phone_number")
    private String Phone_number;
    @Column(name="Created_at")
    private LocalDateTime Created_at;
    @Column(name="Country")
    private String Country;


    public int getAdmin_id() {
        return Admin_id;
    }

    public void setAdmin_id(int admin_id) {
        Admin_id = admin_id;
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

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String phone_number) {
        Phone_number = phone_number;
    }

    public LocalDateTime getCreated_at() {
        return Created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        Created_at = created_at;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }


}
