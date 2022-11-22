package com.example.wineapi.controller;


import com.example.wineapi.data.dto.member.ContainerDTO;
import com.example.wineapi.data.dto.wine.WineDto;
import com.example.wineapi.service.ContainerService;
import com.example.wineapi.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/container")
public class ContainerController {
    private final ContainerService containerService;
    private final WineService wineService;

    @Autowired
    public ContainerController(ContainerService containerService, WineService wineService) {
        this.containerService = containerService;
        this.wineService = wineService;
    }

    @PostMapping("/createContainer")
    public ResponseEntity<ContainerDTO> createContainer(@RequestBody ContainerDTO containerDTO) {
        // user_id, wine_id 중복된거 무조건 삭제 후 새로운 데이터 삽입
        ContainerDTO containerResponseDTO = null;

        containerService.deleteContainer(containerDTO.getUser_id(), containerDTO.getWine_id());
        containerResponseDTO = containerService.saveContainer(containerDTO);

        //null이 리턴되면 삭제
        return ResponseEntity.status(HttpStatus.OK).body(containerResponseDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<ContainerDTO> getContainer(Long id) {
        System.out.println("시작ㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱ");

        ContainerDTO containerResponseDTO = containerService.getContainer(id);

        return ResponseEntity.status(HttpStatus.OK).body(containerResponseDTO);
    }

    @GetMapping("/myContainers") //user_id를 기반으로 나만의 창고를 검색
    public ResponseEntity<List<WineDto>> getMyContainers(Long user_id) { //사용자의 id를 전달
        System.out.println("시작ㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱ");
        List<Long> li = containerService.getMyContainers(user_id); // 사용자
        System.out.println("출력ㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱ");
        if(li==null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        //return ResponseEntity.status(HttpStatus.OK).body(li);

        //***********************************************
        //여기에 추가로 wine에 매핑 시킨 리스트을 리턴 해줘야함.
        //***********************************************
        List<WineDto> result = null;
        for(int i=0; i<li.size(); i++) {
            result.add(wineService.wineDtoById(Long.valueOf(i)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);



    }
//  delete는 굳이 ... 창고에 넣을 때 자동으로 삭제하는 기능 구현함
//    @DeleteMapping()//xxx
//    public ResponseEntity<String> deleteContainer(Long id) throws Exception {
//        //containerService.deleteContainer(id);
//
//        return ResponseEntity.status(HttpStatus.OK).body("정삭 삭제");
//    }
//
//    @DeleteMapping("/deleteMyContainer") //xxx
//    public ResponseEntity<String> deleteMyContainer(Long user_id, Long wine_id) throws Exception {
//        //containerService.deleteMyContainer(user_id, wine_id);
//        return null;
//    }
}
