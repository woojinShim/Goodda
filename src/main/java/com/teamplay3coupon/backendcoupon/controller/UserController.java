package com.teamplay3coupon.backendcoupon.controller;

import com.teamplay3coupon.backendcoupon.dto.*;
import com.teamplay3coupon.backendcoupon.exception.CustomErrorException;
import com.teamplay3coupon.backendcoupon.model.User;
import com.teamplay3coupon.backendcoupon.security.UserDetailsImpl;
import com.teamplay3coupon.backendcoupon.service.UserService;
import com.teamplay3coupon.backendcoupon.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @PostMapping("/api/user/signup")
    public ResponseDto signUp(@RequestBody SignupRequestDto signupRequestDto) {
        System.out.println("UserController:"+signupRequestDto.getNickname());
        return userService.signup(signupRequestDto);
    }

    // 로그인
    @PostMapping("/api/user/login")
    public ResponseDto login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        User user = userService.login(requestDto.getUserEmail(), requestDto.getPassword());
        String checkedUserEmail = user.getUserEmail();
        String token = jwtTokenProvider.createToken(checkedUserEmail);
        String checkedNickname = user.getNickname();

        UserResponseDataDto dataDto = new UserResponseDataDto(token,checkedUserEmail,checkedNickname);

        response.setHeader("X-AUTH-TOKEN", token);
        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        //body에도 보내주기 혹시모르니까
        return new ResponseDto("success",dataDto);
    }
    //유저이름 중복확인
    @PostMapping("/api/user/redunancy")
    public ResponseDto checkRedunancy(@RequestBody UserEmailDto username){
        System.out.println("유저네임: "+username.getUserEmail());
        userService.checkRedunbancy(username.getUserEmail());

        return new ResponseDto("success","중복되지 않습니다");
    }



    //계정삭제
    @DeleteMapping("/api/user/delete")
    public ResponseDto deleteUser(
            @RequestBody UserDeleteRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        checkLogin(userDetails);
        // 패스워드 암호화
        if (!passwordEncoder.matches(requestDto.getPassword(), userDetails.getPassword())) {
            throw new CustomErrorException("비밀번호가 맞지 않습니다.");
        }
        userService.delete(userDetails.getUser().getId());
        return new ResponseDto("success", "계정이 삭제되었습니다");
    }






    //게정수정




    private void checkLogin(UserDetailsImpl userDetails) {
        if(userDetails == null){
            throw new CustomErrorException("로그인 사용자만 이용가능합니다.");
        }
    }

}
