package com.example.wineapi.controller;


import com.example.wineapi.data.dto.container.ContainerViewDto;
import com.example.wineapi.data.dto.member.ContainerDTO;
import com.example.wineapi.data.dto.wine.WineDto;
import com.example.wineapi.jwt.JwtAuthenticationProvider;
import com.example.wineapi.service.ContainerService;
import com.example.wineapi.service.MemberService;
import com.example.wineapi.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/container")
public class ContainerController {
    private final MemberService memberService;
    private final ContainerService containerService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    public ContainerController(MemberService memberService, ContainerService containerService, JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.memberService = memberService;
        this.containerService = containerService;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    @GetMapping("/getContainer/v1/{id}") // 서버 내수용, 문서 작업하지말것
    public ResponseEntity<ContainerDTO> getContainer(@PathVariable Long id) {
        ContainerDTO containerResponseDTO = containerService.getContainer(id);

        return ResponseEntity.status(HttpStatus.OK).body(containerResponseDTO);
    }


    @GetMapping("/getContainers/v1") //user_id를 기반으로 나만의 창고를 검색
    public ResponseEntity<List<ContainerViewDto>> getMyContainers(@RequestHeader("X-AUTH-TOKEN") String req) { //사용자의 id를 전달
        String email = jwtAuthenticationProvider.getUserPk(req);
        Long user_id = memberService.getId(email);
        List<ContainerViewDto> result = containerService.getMyContainers(user_id); //
        if(result == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/modifyContainer/v1")
    public ResponseEntity<ContainerDTO> modifyContainer(@RequestBody ContainerDTO containerDTO, HttpServletRequest request) {
        String token = request.getHeader("X-AUTH-TOKEN");

        if (token != null) {
            String userEmail = jwtAuthenticationProvider.getUserPk(token);
            Long userId = memberService.getId(userEmail);
            containerService.deleteContainer(userId, containerDTO.getWine_id());
            ContainerDTO result = containerService.saveContainer(userId, containerDTO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/deleteContainer/v1/{wineId}")
    public ResponseEntity<String> deleteMyContainer(@PathVariable("wineId") Long wineId, HttpServletRequest request) throws Exception {
        String token = request.getHeader("X-AUTH-TOKEN");

        if (token != null) {
            String userEmail = jwtAuthenticationProvider.getUserPk(token);
            Long userId = memberService.getId(userEmail);
            containerService.deleteContainer(userId, wineId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("miss member token", HttpStatus.BAD_REQUEST);
        }
    }
}
