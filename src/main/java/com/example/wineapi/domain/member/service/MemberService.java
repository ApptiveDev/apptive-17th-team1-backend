package com.example.wineapi.domain.member.service;

import com.example.wineapi.domain.member.dto.MemberDTO;

public interface MemberService {
    MemberDTO saveMember(MemberDTO memberDTO);
    MemberDTO getMember(Long id);
    Long getId(String email);
    void deleteMember(Long id) throws Exception;
    boolean login(MemberDTO memberDTO);

    boolean isDuplicated(String email);
}
