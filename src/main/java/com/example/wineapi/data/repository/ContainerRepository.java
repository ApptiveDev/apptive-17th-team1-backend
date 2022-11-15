package com.example.wineapi.data.repository;

import com.example.wineapi.data.entity.member.Container;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContainerRepository extends JpaRepository<Container, Long> {
}
