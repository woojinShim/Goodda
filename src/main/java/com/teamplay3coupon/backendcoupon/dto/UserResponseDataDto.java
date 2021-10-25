package com.teamplay3coupon.backendcoupon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDataDto {
    String token;
    String userEmail;
    String nickname;
}