package com.example.wineapi.data.dao;

import com.example.wineapi.data.dto.MemberDTO;
import com.example.wineapi.data.entity.member.Member;

public interface MemberDAO {
    Member insertMember(Member member);
    Member selectMember(Long id);
    void deleteMember(Long id) throws Exception;

    Member loginCheck(String email, String pass);

    Member findByEmail(String email);
}
