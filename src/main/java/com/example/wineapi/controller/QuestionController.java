package com.example.wineapi.controller;

import com.example.wineapi.data.dto.member.ContainerDTO;
import com.example.wineapi.data.dto.question.AnswerDto;
import com.example.wineapi.data.dto.question.QuestionDto;
import com.example.wineapi.data.dto.wine.WineDto;
import com.example.wineapi.jwt.JwtAuthenticationProvider;
import com.example.wineapi.service.impl.ContainerServiceImpl;
import com.example.wineapi.service.impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.wineapi.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    private final QuestionService questionService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final MemberServiceImpl memberService;
    private final ContainerServiceImpl containerService;

    @Autowired
    public QuestionController(QuestionService questionService, JwtAuthenticationProvider jwtAuthenticationProvider, MemberServiceImpl memberService, ContainerServiceImpl containerService) {
        this.questionService = questionService;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.memberService = memberService;
        this.containerService = containerService;
    }


    @RequestMapping(value = "/category/v1/{category}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<QuestionDto>> QuestionByCategory(@PathVariable("category") Integer category) {

        if (category > 3) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        ArrayList<QuestionDto> result = questionService.QuestionDtoByCategory(category);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/answer/v1")
    public ResponseEntity<WineDto> recommendWine(@RequestBody AnswerDto answerDto, HttpServletRequest request) {
        if(request.getAttribute("exception") == HttpStatus.BAD_REQUEST) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        String token = request.getHeader("X-AUTH-TOKEN");
        WineDto result = questionService.findSimilarWineDto(answerDto);

        /** 로그인한 상태에서 추천시 추천기록 저장 */
        if (token != null) {
            String userEmail = jwtAuthenticationProvider.getUserPk(token);
            Long userId = memberService.getId(userEmail);
            ContainerDTO containerDTO = new ContainerDTO(userId, result.getId(), false);
            containerService.deleteContainer(userId, result.getId());
            containerService.saveContainer(userId ,containerDTO);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
