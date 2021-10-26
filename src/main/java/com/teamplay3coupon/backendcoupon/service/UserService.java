package com.teamplay3coupon.backendcoupon.service;

import com.teamplay3coupon.backendcoupon.model.User;
import com.teamplay3coupon.backendcoupon.repository.UserRepository;
import com.teamplay3coupon.backendcoupon.dto.ResponseDto;
import com.teamplay3coupon.backendcoupon.dto.SignupRequestDto;
import com.teamplay3coupon.backendcoupon.exception.CustomErrorException;
import com.teamplay3coupon.backendcoupon.exception.UnauthenticatedException;
import com.teamplay3coupon.backendcoupon.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //회원가입
    public ResponseDto signup(SignupRequestDto signupRequestDto) {
        String userEmail = signupRequestDto.getUserEmail();
        String nickname = signupRequestDto.getNickname();//실명
        System.out.println("UserService:"+userEmail);

//        회원 ID 중복 확인
        checkRedunbancy(userEmail);
        //패스워드 암호화
        String encodedPassword= passwordEncoder.encode(signupRequestDto.getPassword());

        User user = new User(userEmail,nickname,encodedPassword);
        System.out.println("UserService의 User:"+user.getUserEmail());
        User savedUser = userRepository.save(user);
        System.out.println(savedUser.getUserEmail());
        return new ResponseDto("success","회원가입 성공");
    }
    public void checkRedunbancy(String userEmail) {
        Optional<User> found = userRepository.findByUserEmail(userEmail);
        if (found.isPresent()) {
            throw new CustomErrorException("중복된 유저이메일이 존재합니다.");
        }
    }
    //로그인
    public User login(String userEmail, String password) {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new CustomErrorException("유저네임을 찾을 수 없습니다.")
        );

        // 패스워드 암호화
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomErrorException("비밀번호가 맞지 않습니다.");
        }
        return user;
    }
    public User userFromUserDetails(UserDetails userDetails) {
        if ( userDetails instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) userDetails).getUser();
        } else {
            throw new UnauthenticatedException("로그인이 필요합니다.");
        }
    }

    public User loadLoginUser(UserDetailsImpl userDetails){
        return userRepository.findByUserEmail(userDetails.getUsername()).orElseThrow(
                ()->new CustomErrorException("로그인된 유저의 정보를 찾을 수 없습니다")
        );
    }
    //계정삭제
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    //게정수정
}
