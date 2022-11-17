package com.example.wineapi.controller;

import com.example.wineapi.data.dto.member.ContainerDTO;
import com.example.wineapi.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/container")
public class ContainerController {
    private final ContainerService containerService;

    @Autowired
    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }


    @PostMapping("/createContainer")
    public ResponseEntity<ContainerDTO> createContainer(@RequestBody ContainerDTO containerDTO) {
        //애초에 창고는 is_like를 true로 시작한다. 이후에 false로 바뀌면 db에서 삭제.
        //창고에서 삭제는 user_id, wine_id를 받아 해당하는 값 삭제
        // true로 왔을 때는 바로 저장, false가 오면 있는 db 삭제해야함
        ContainerDTO containerResponseDTO = null;

        containerService.deleteContainer(containerDTO.getUser_id(), containerDTO.getWine_id());
        containerResponseDTO = containerService.saveContainer(containerDTO);

        //null이 리턴되면 삭제
        return ResponseEntity.status(HttpStatus.OK).body(containerResponseDTO);
    }

    @GetMapping()
    public ResponseEntity<ContainerDTO> getContainer(Long id) {
        ContainerDTO containerResponseDTO = containerService.getContainer(id);

        return ResponseEntity.status(HttpStatus.OK).body(containerResponseDTO);
    }

    @GetMapping("/myContainers") //user_id를 기반으로 나만의 창고를 검색
    public ResponseEntity<List<Long>> getMyContainers(Long user_id) { //사용자의 id를 전달
        List<Long> li = containerService.getMyContainers(user_id);
        if(li==null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(li);

        //***********************************************
        //여기에 추가로 wine에 매핑 시킨 리스트을 리턴 해줘야함.
        //***********************************************
        /*
        List<Wine> result = null;
        for(int i=0; i<li.length(); i++) {
            containerService.getMyWine(li[i]);
        }
        */
    }

    @DeleteMapping()//xxx
    public ResponseEntity<String> deleteContainer(Long id) throws Exception {
        //containerService.deleteContainer(id);

        return ResponseEntity.status(HttpStatus.OK).body("정삭 삭제");
    }

    @DeleteMapping("/deleteMyContainer") //xxx
    public ResponseEntity<String> deleteMyContainer(Long user_id, Long wine_id) throws Exception {
        //containerService.deleteMyContainer(user_id, wine_id);
        return null;
    }
}
