package com.example.wineapi.service.impl;

import com.example.wineapi.data.dao.MemberDAO;
import com.example.wineapi.data.dto.member.MemberDTO;
import com.example.wineapi.data.entity.member.Member;
import com.example.wineapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;

    @Autowired
    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public MemberDTO saveMember(MemberDTO memberDTO) {
        Member member = new Member();
        member.setEmail(memberDTO.getEmail());
        member.setPass(memberDTO.getPass());

        Member savedMember = memberDAO.insertMember(member);

        MemberDTO memberResponseDTO = new MemberDTO(savedMember.getEmail(), savedMember.getPass());
        return memberResponseDTO;
    }

    @Override
    public MemberDTO getMember(Long id) {
        Member member = memberDAO.selectMember(id);

        MemberDTO memberResponseDTO = new MemberDTO(member.getEmail(), member.getPass());

        return memberResponseDTO;
    }

    @Override
    public void deleteMember(Long id) throws Exception {
        memberDAO.deleteMember(id);
    }

    public boolean login(MemberDTO memberDTO) {
        Member member = memberDAO.loginCheck(memberDTO.getEmail(), memberDTO.getPass());
        if(member == null) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean isDuplicated(String email) {
        if (memberDAO.findByEmail(email) == null){
            return false;
        }
        else {
            return true;
        }
    }

}
