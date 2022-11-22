package com.example.wineapi.controller;

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

    // join으로 구현 해놓음 굳이 사용안해도 될듯....
    @RequestMapping(value = "/createMember", method = RequestMethod.POST)
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        if(memberService.isDuplicated(memberDTO.getEmail())) { //이메일 중복시
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        MemberDTO memberResponseDTO = memberService.saveMember(memberDTO); //처음이면 회원

        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDTO);
    }

    @GetMapping("/getMember/{id}") //없을 때 구현 아직
    public ResponseEntity<MemberDTO> getMember(@PathVariable Long id) { //id로 회원 검색 xxx
        MemberDTO memberResponseDTO = memberService.getMember(id);

        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDTO);
    }

    @GetMapping("/isDuplicated/{email}")
    public ResponseEntity<Boolean> isDuplicated(@PathVariable String email) { //id로 회원 검색 xxx
        boolean check = memberService.isDuplicated(email);
        return ResponseEntity.status(HttpStatus.OK).body(check);
    }

    // 아이디중복여부를 판단하는 컨트롤러 추가 고려!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    @DeleteMapping("/deleteMember")
    public ResponseEntity<String> deleteMember(Long id) throws Exception { // xxx
        memberService.deleteMember(id);
        
        return ResponseEntity.status(HttpStatus.OK).body("삭제");
    }


    @PostMapping("/join") //회원가입 중복체크
    public void join(@RequestBody MemberDTO memberDTO){
        userRepository.save(Member.builder()
                .email(memberDTO.getEmail())
                .pass(passwordEncoder.encode(memberDTO.getPass()))
                .name(memberDTO.getName())
                .gender(memberDTO.getGender())
                .age(memberDTO.getAge())
                .roles(Collections.singletonList("ROLE_USER"))
                .build());

    }

    @PostMapping("/login") //로그인 시 이메일, 비번만 JSON으로 줘도됨
    public MemberDTO login(@RequestBody MemberDTO memberDTO, HttpServletResponse response) {
        Member member = userRepository.findByEmail(memberDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(memberDTO.getPass(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        String token = jwtAuthenticationProvider.createToken(member.getUsername(), member.getRoles());
        response.setHeader("X-AUTH-TOKEN", token);
        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        MemberDTO m = new MemberDTO(member.getEmail(), member.getPass(), member.getName(), member.getGender(), member.getAge());

        return m;
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response){
        Cookie cookie = new Cookie("X-AUTH-TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

//    @GetMapping("/info")
//    public MemberDTO getInfo(){
//        Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if(details != null && !(details instanceof  String)) {
//
//            return new MemberDTO((Member) details);
//        }
//        return null;
//    }
}
