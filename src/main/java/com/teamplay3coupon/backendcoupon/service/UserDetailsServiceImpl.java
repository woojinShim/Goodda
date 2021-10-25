package com.teamplay3coupon.backendcoupon.service;

import com.teamplay3coupon.backendcoupon.model.User;
import com.teamplay3coupon.backendcoupon.repository.UserRepository;
import com.teamplay3coupon.backendcoupon.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    //로그인할 때 들어온 username으로 DB에서 정보 찾기
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + userEmail));

        return new UserDetailsImpl(user);
    }
}