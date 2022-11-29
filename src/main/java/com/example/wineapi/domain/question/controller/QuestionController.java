package com.example.wineapi.domain.question.controller;

import com.example.wineapi.domain.container.dto.ContainerDTO;
import com.example.wineapi.domain.question.service.QuestionService;
import com.example.wineapi.domain.question.dto.AnswerDto;
import com.example.wineapi.domain.question.dto.QuestionDto;
import com.example.wineapi.domain.wine.dto.WineDto;
import com.example.wineapi.jwt.JwtAuthenticationProvider;
import com.example.wineapi.domain.container.service.ContainerServiceImpl;
import com.example.wineapi.domain.member.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
