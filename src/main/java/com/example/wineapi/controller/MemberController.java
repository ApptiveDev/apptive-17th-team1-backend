package com.example.wineapi.controller;

import com.example.wineapi.data.dto.MemberDTO;
import com.example.wineapi.data.entity.Member;
import com.example.wineapi.data.repository.UserRepository;
import com.example.wineapi.jwt.JwtAuthenticationProvider;
import com.example.wineapi.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

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

    @RequestMapping(value = "/createMember", method = RequestMethod.POST)
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        if(memberService.isDuplicated(memberDTO.getEmail())) { //이메일 중복시
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        MemberDTO memberResponseDTO = memberService.saveMember(memberDTO); //처음이면 회원

        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDTO);
    }

    @GetMapping("/getMember/{id}")
    public ResponseEntity<MemberDTO> getMember(@PathVariable Long id) { //id로 회원 검색 xxx
        MemberDTO memberResponseDTO = memberService.getMember(id);

        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDTO);
    }
    
    @DeleteMapping()
    public ResponseEntity<String> deleteMember(Long id) throws Exception { // xxx
        memberService.deleteMember(id);
        
        return ResponseEntity.status(HttpStatus.OK).body("삭제");
    }

//    @PostMapping("/login") //리턴으로 무엇을 줘야지??? 세션 값???
//    public ResponseEntity<String> login(@ModelAttribute MemberDTO memberDTO, HttpServletRequest request) {
//        HttpSession session;
//        if(memberService.login(memberDTO)) { //이메일, 비밀번호 일치시
//            session = request.getSession(); // 세션 생성
//            session.setAttribute("member", memberDTO.getEmail());
//            //System.out.print(session.getAttribute("member"));
//            return ResponseEntity.status(HttpStatus.OK).body("success");
//        }
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("fail");
//    }

    @PostMapping("/join")
    public void join(@RequestBody MemberDTO memberDTO){
        userRepository.save(Member.builder()
                .email(memberDTO.getEmail())
                .pass(passwordEncoder.encode(memberDTO.getPass()))
                .roles(Collections.singletonList("ROLE_USER"))
                .build());

    }

    @PostMapping("/login")
    public MemberDTO login(@RequestBody MemberDTO memberDTO, HttpServletResponse response) {
        //System.out.println("ddddddddddddddddddddddddddddddddddddddddd" + memberDTO);
        Member member = userRepository.findByEmail(memberDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        //System.out.println();
        if (!passwordEncoder.matches(memberDTO.getPass(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        String token = jwtAuthenticationProvider.createToken(member.getUsername(), member.getRoles());
        response.setHeader("X-AUTH-TOKEN", token);
        System.out.println("토큰ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ" + token);
        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        MemberDTO m = new MemberDTO(member.getEmail(), member.getPass());

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
