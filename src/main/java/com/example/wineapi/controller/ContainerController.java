package com.example.wineapi.controller;

import com.example.wineapi.data.dto.ContainerDTO;
import com.example.wineapi.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/container")
public class ContainerController {
    private final ContainerService containerService;

    @Autowired
    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @PostMapping()
    public ResponseEntity<ContainerDTO> createMember(@RequestBody ContainerDTO containerDTO) {
        // 와인 id와 사용자 id를 받아 와야 함
        // 생성할 때 모든 id가 동일하면 주의 해야함. -> 사전에 db에서 검색 후 생성하는 작업 필요
        ContainerDTO containerResponseDTO = containerService.saveContainer(containerDTO);

        return ResponseEntity.status(HttpStatus.OK).body(containerResponseDTO);
    }

    @GetMapping()
    public ResponseEntity<ContainerDTO> getMember(Long id) {
        ContainerDTO containerResponseDTO = containerService.getContainer(id);

        return ResponseEntity.status(HttpStatus.OK).body(containerResponseDTO);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteMember(Long id) throws Exception {
        containerService.deleteContainer(id);

        return ResponseEntity.status(HttpStatus.OK).body("정삭 삭제");
    }
}
