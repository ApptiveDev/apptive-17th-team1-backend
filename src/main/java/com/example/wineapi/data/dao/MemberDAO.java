package com.example.wineapi.data.dao;

import com.example.wineapi.data.entity.Member;

public interface MemberDAO {
    Member insertMember(Member member);
    Member selectMember(Long id);
    void deleteMember(Long id) throws Exception;
}
