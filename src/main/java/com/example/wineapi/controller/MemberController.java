package com.example.wineapi.controller;

import com.example.wineapi.data.dto.MemberDTO;
import com.example.wineapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping()
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        if(memberService.isDuplicated(memberDTO.getEmail())) { //이메일 중복시
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        MemberDTO memberResponseDTO = memberService.saveMember(memberDTO); //처음이면 회원

        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDTO);
    }

    @GetMapping()
    public ResponseEntity<MemberDTO> getMember(Long id) { //id로 회원 검색 xxx
        MemberDTO memberResponseDTO = memberService.getMember(id);

        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDTO);
    }
    
    @DeleteMapping()
    public ResponseEntity<String> deleteMember(Long id) throws Exception { // xxx
        memberService.deleteMember(id);
        
        return ResponseEntity.status(HttpStatus.OK).body("삭제");
    }

    @PostMapping("/login") //리턴으로 무엇을 줘야지??? 세션 값???
    public ResponseEntity<String> login(@ModelAttribute MemberDTO memberDTO, HttpServletRequest request) {
        HttpSession session;
        if(memberService.login(memberDTO)) { //이메일, 비밀번호 일치시
            session = request.getSession(); // 세션 생성
            session.setAttribute("member", memberDTO.getEmail());
            //System.out.print(session.getAttribute("member"));
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("fail");
    }
}
