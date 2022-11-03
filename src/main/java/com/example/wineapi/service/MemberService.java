package com.example.wineapi.service;

import com.example.wineapi.data.dto.MemberDTO;

public interface MemberService {
    MemberDTO saveMember(MemberDTO memberDTO);
    MemberDTO getMember(Long id);
    void deleteMember(Long id) throws Exception;
    boolean login(MemberDTO memberDTO);

    boolean isDuplicated(String email);
}
