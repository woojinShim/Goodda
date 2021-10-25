package com.teamplay3coupon.backendcoupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BackendCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendCouponApplication.class, args);
    }

}
