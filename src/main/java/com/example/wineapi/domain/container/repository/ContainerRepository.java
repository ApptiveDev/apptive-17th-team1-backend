package com.example.wineapi.domain.container.repository;

import com.example.wineapi.domain.container.entity.Container;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerRepository extends JpaRepository<Container, Long> {
}
