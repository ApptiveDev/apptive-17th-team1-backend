package com.example.wineapi.data.repository;

import com.example.wineapi.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
