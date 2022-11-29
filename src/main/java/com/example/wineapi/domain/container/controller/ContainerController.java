package com.example.wineapi.domain.container.controller;


import com.example.wineapi.domain.container.dto.ContainerDTO;
import com.example.wineapi.domain.container.dto.ContainerViewDto;
import com.example.wineapi.domain.container.service.ContainerService;
import com.example.wineapi.domain.member.service.MemberService;
import com.example.wineapi.jwt.JwtAuthenticationProvider;
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
    public ResponseEntity<ContainerDTO> getContainer(@PathVariable Long id, HttpServletRequest request) {
        if(request.getAttribute("exception") == HttpStatus.BAD_REQUEST) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        ContainerDTO containerResponseDTO = containerService.getContainer(id);

        return ResponseEntity.status(HttpStatus.OK).body(containerResponseDTO);
    }


    @GetMapping("/getContainers/v1")
    public ResponseEntity<List<ContainerViewDto>> getMyContainers(@RequestHeader("X-AUTH-TOKEN") String req, HttpServletRequest request) {
        if(request.getAttribute("exception") == HttpStatus.BAD_REQUEST) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if (req == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        String email = jwtAuthenticationProvider.getUserPk(req);
        Long user_id = memberService.getId(email);
        List<ContainerViewDto> result = containerService.getMyContainers(user_id);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/modifyContainer/v1")
    public ResponseEntity<ContainerDTO> modifyContainer(@RequestBody ContainerDTO containerDTO, HttpServletRequest request) {
        if(request.getAttribute("exception") == HttpStatus.BAD_REQUEST) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

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
        if (request.getAttribute("exception") == HttpStatus.BAD_REQUEST) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid token");
        }

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
