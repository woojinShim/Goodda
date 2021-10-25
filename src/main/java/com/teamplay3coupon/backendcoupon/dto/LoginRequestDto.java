package com.teamplay3coupon.backendcoupon.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDto {
    private String userEmail;

    private String password;
}
