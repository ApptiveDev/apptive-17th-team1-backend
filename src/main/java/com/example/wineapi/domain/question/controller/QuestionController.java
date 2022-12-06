package com.example.wineapi.domain.question.controller;

import com.example.wineapi.domain.container.dto.ContainerDTO;
import com.example.wineapi.domain.container.service.ContainerServiceImpl;
import com.example.wineapi.domain.member.service.MemberServiceImpl;
import com.example.wineapi.domain.question.dto.AnswerDto;
import com.example.wineapi.domain.question.dto.QuestionDto;
import com.example.wineapi.domain.question.service.QuestionService;
import com.example.wineapi.domain.wine.dto.WineDto;
import com.example.wineapi.global.error.ErrorCode;
import com.example.wineapi.global.error.exception.CustomException;
import com.example.wineapi.jwt.JwtAuthenticationProvider;
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

        if (category > 3) throw new CustomException(ErrorCode.QUESTION_NOT_FOUND);
        
        ArrayList<QuestionDto> result = questionService.QuestionDtoByCategory(category);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/answer/v1")
    public ResponseEntity<WineDto> recommendWine(@RequestBody AnswerDto answerDto, HttpServletRequest request) {
        if (request.getAttribute("exception") == HttpStatus.BAD_REQUEST) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_MEMBER);
        }

        String token = request.getHeader("X-AUTH-TOKEN");
        WineDto wineDto = questionService.findSimilarWineDto(answerDto);

        /* 로그인한 상태에서 추천시 추천기록 저장 */
        if (token != null) {
            String userEmail = jwtAuthenticationProvider.getUserPk(token);
            Long userId = memberService.getId(userEmail);
            ContainerDTO containerDTO = new ContainerDTO(userId, wineDto.getId(), false);

            /* 와인창고 존재여부 확인 */
            ContainerDTO myContainerDTO = containerService.getContainer(userId, wineDto.getId());
            if (myContainerDTO.getWine_id() == null) {  // 존재하지 않을때 추가
                containerService.saveContainer(userId ,containerDTO);
            }
        }

        return new ResponseEntity<>(wineDto, HttpStatus.OK);
    }

}
