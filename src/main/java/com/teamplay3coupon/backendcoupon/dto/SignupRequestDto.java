package com.teamplay3coupon.backendcoupon.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequestDto {
    private String userEmail;
    private String nickname;
    private String password;
}
