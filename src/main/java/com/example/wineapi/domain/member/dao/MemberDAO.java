package com.example.wineapi.domain.member.dao;

import com.example.wineapi.domain.member.entity.Member;

public interface MemberDAO {
    Member insertMember(Member member);
    Member selectMember(Long id);
    Long selectId(String email);
    void deleteMember(Long id) throws Exception;

    Member loginCheck(String email, String pass);

    Member findByEmail(String email);
}
