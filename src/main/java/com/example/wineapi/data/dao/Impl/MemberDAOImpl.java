package com.example.wineapi.data.dao.Impl;

import com.example.wineapi.data.dao.MemberDAO;
import com.example.wineapi.data.entity.member.Member;
import com.example.wineapi.data.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
@Component
public class MemberDAOImpl implements MemberDAO {
    private final MemberRepository memberRepository;
    @PersistenceContext
    private EntityManager em;

    @Autowired
    public MemberDAOImpl (MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member insertMember(Member member) { //회원 가입 시 중복 체크...
        Member savedMember = memberRepository.save(member);

        return savedMember;
    }

    @Override
    public Member selectMember(Long id) {
        Member selectMember = memberRepository.getById(id);

        return selectMember;
    }

    @Override
    public Long selectId(String email) {
        return  em.createQuery("select m.id from Member m where m.email =: email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
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

    public Member loginCheck(String email, String pass) {
        List<Member> li =  em.createQuery("select m from Member m where m.email =: email and m.pass =: pass ", Member.class)
                .setParameter("email", email)
                .setParameter("pass", pass)
                .getResultList();
        if(li.size() == 0)
            return null;
        else
            return li.get(0);
    }

    public Member findByEmail(String email) {
        List<Member> li = em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
        if(li.size() == 0)
            return null;
        else
            return li.get(0);
    }
}
