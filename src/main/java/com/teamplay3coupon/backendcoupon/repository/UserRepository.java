package com.teamplay3coupon.backendcoupon.repository;

import com.teamplay3coupon.backendcoupon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserEmail(String userEmail);
}
