package com.example.wineapi.controller;


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

import java.util.List;

@RestController
@RequestMapping("/container")
public class ContainerController {
    private final MemberService memberService;
    private final ContainerService containerService;
    private final WineService wineService;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    public ContainerController(MemberService memberService, ContainerService containerService, WineService wineService, JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.memberService = memberService;
        this.containerService = containerService;
        this.wineService = wineService;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }


    @PostMapping("/createContainer/v1") //창고 생성

    public ResponseEntity<ContainerDTO> createContainer(@RequestBody ContainerDTO containerDTO) {
        // user_id, wine_id 중복된거 무조건 삭제 후 새로운 데이터 삽입
        ContainerDTO containerResponseDTO = null;

        containerService.deleteContainer(containerDTO.getUser_id(), containerDTO.getWine_id());
        containerResponseDTO = containerService.saveContainer(containerDTO);

        //null이 리턴되면 삭제
        return ResponseEntity.status(HttpStatus.OK).body(containerResponseDTO);
    }

    @GetMapping("/getContainer/v1/{id}") // 확인용. 필요하지는 않은듯
    public ResponseEntity<ContainerDTO> getContainer(@PathVariable Long id) {
        ContainerDTO containerResponseDTO = containerService.getContainer(id);

        return ResponseEntity.status(HttpStatus.OK).body(containerResponseDTO);
    }

    @GetMapping("/getMyContainers/v1") //user_id를 기반으로 나만의 창고를 검색
    public ResponseEntity<List<WineDto>> getMyContainers(@RequestHeader("X-AUTH-TOKEN") String req) { //사용자의 id를 전달
        String email = jwtAuthenticationProvider.getUserPk(req);
        Long user_id = memberService.getId(email);
        List<Long> li = containerService.getMyContainers(user_id); //
        if(li==null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

        List<WineDto> result = null;
        for(int i=0; i<li.size(); i++) {
            result.add(wineService.wineDtoById(Long.valueOf(i)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @DeleteMapping("/deleteMyContainer") //xxx
    public ResponseEntity<String> deleteMyContainer(Long user_id, Long wine_id) throws Exception {
        containerService.deleteContainer(user_id, wine_id);
        return null;
    }
}
