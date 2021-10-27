package com.teamplay3coupon.backendcoupon.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String telecom;

    @Column(nullable = false)
    private String cardType;

    @Column(nullable = false)
    private String type1;

    @Column(nullable = false)
    private String type2;

    @Column(nullable = false)
    private String type3;

    public User(String userEmail, String nickname, String password, String telecom, String cardType, String type1, String type2, String type3){
        this.userEmail = userEmail;
        this.nickname = nickname;
        this.password = password;
        this.telecom = telecom;
        this.cardType = cardType;
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
    }

    public User() {

    }
    public void updateUser(String nickname, String telecom, String cardType, String type1, String type2, String type3){
        this.nickname = nickname;
        this.telecom = telecom;
        this.cardType = cardType;
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
    }
}
