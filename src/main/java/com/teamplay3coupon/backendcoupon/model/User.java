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

    public User(String userEmail, String nickname, String password){
        this.userEmail = userEmail;
        this.nickname = nickname;
        this.password = password;
    }

    public User() {

    }
}
