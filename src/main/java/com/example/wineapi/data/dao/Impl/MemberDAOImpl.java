package com.example.wineapi.data.dao.Impl;

import com.example.wineapi.data.dao.MemberDAO;
import com.example.wineapi.data.entity.Member;
import com.example.wineapi.data.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class MemberDAOImpl implements MemberDAO {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberDAOImpl (MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member insertMember(Member member) {
        Member savedMember = memberRepository.save(member);

        return savedMember;
    }

    @Override
    public Member selectMember(Long id) {
        Member selectMember = memberRepository.getById(id);

        return selectMember;
    }

    @Override
    public void deleteMember(Long id) throws Exception {
        Optional<Member> selectedMember = memberRepository.findById(id);

        if(selectedMember.isPresent()) {
            Member member = selectedMember.get();

            memberRepository.delete(member);
        }
        else {
            throw new Exception();
        }
    }
}
