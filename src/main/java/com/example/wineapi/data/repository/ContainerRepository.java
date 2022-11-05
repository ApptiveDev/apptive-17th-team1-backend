package com.example.wineapi.data.repository;

import com.example.wineapi.data.entity.member.Container;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerRepository extends JpaRepository<Container, Long> {
}
