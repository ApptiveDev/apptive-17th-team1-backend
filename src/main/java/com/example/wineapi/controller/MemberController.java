package com.example.wineapi.controller;

import com.example.wineapi.data.dto.MemberDTO;
import com.example.wineapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        MemberDTO memberResponseDTO = memberService.saveMember(memberDTO);

        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDTO);
    }

    @GetMapping()
    public ResponseEntity<MemberDTO> getMember(Long id) {
        MemberDTO memberResponseDTO = memberService.getMember(id);

        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDTO);
    }
    
    @DeleteMapping()
    public ResponseEntity<String> deleteMember(Long id) throws Exception {
        memberService.deleteMember(id);
        
        return ResponseEntity.status(HttpStatus.OK).body("정삭 삭제");
    }
}
