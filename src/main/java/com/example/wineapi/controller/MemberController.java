package com.example.wineapi.controller;

import com.example.wineapi.data.dto.member.LoginDTO;
import com.example.wineapi.data.dto.member.TokenDTO;
import com.example.wineapi.data.entity.member.Member;
import com.example.wineapi.data.repository.UserRepository;
import com.example.wineapi.jwt.JwtAuthenticationProvider;
import com.example.wineapi.data.dto.member.MemberDTO;
import com.example.wineapi.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;


@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;


    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


//    @GetMapping("/getMember/{id}")
//    public ResponseEntity<MemberDTO> getMember(@PathVariable Long id) {
//        MemberDTO memberResponseDTO = memberService.getMember(id);
//
//        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDTO);
//    }

    /** 회원가입 */
    @PostMapping("/join/v1")
    public void join(@RequestBody MemberDTO memberDTO){
        if(memberService.isDuplicated(memberDTO.getEmail())) { // 이메일 중복시
            return;
        }
        userRepository.save(Member.builder()
                .email(memberDTO.getEmail())
                .pass(passwordEncoder.encode(memberDTO.getPass()))
                .name(memberDTO.getName())
                .gender(memberDTO.getGender())
                .age(memberDTO.getAge())
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
    }

    /** 로그인 */
    @PostMapping("/login/v1") //로그인 시 이메일, 비번만 JSON으로 줘도됨 -> 로그인 필요 x
    public TokenDTO login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        Member member = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(loginDTO.getPass(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        String token = jwtAuthenticationProvider.createToken(member.getUsername(), member.getRoles()); //토큰 생성 -> 입력을 회원 ID로 바꿔야 수월
        response.setHeader("X-AUTH-TOKEN", token);
//        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
//        response.addCookie(cookie);
        TokenDTO t = new TokenDTO(member.getEmail(), member.getPass(), member.getName(), member.getGender(), member.getAge(), token);

        return t;
    }

    /** 회원 삭제 */
    @DeleteMapping("/deleteMember/v1")
    public ResponseEntity<String> deleteMember(HttpServletRequest request) throws Exception {
        if(request.getAttribute("exception") == HttpStatus.BAD_REQUEST) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid token");
        }
        String token = request.getHeader("X-AUTH-TOKEN");

        if (token != null) {
            String userEmail = jwtAuthenticationProvider.getUserPk(token);
            Long userId = memberService.getId(userEmail);
            memberService.deleteMember(userId);
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot find member");
        }

        return ResponseEntity.status(HttpStatus.OK).body("delete success");
    }
}
