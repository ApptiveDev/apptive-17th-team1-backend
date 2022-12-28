package com.example.wineapi.domain.question.service;

import com.example.wineapi.domain.question.dto.AnswerDto;
import com.example.wineapi.domain.question.dto.LikertQuestionDto;
import com.example.wineapi.domain.question.dto.MultipleChoiceQuestionDto;
import com.example.wineapi.domain.question.dto.QuestionDto;
import com.example.wineapi.domain.question.entity.LikertScale;
import com.example.wineapi.domain.question.entity.Question;
import com.example.wineapi.domain.question.entity.QuestionOption;
import com.example.wineapi.domain.question.repository.QuestionRepository;
import com.example.wineapi.domain.wine.dto.WineDto;
import com.example.wineapi.domain.wine.dto.WineInfoDto;
import com.example.wineapi.domain.wine.entity.Wine;
import com.example.wineapi.domain.wine.repository.WineRepository;
import com.example.wineapi.domain.wine.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Transactional
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final WineRepository wineRepository;
    private final WineService wineService;


    @Autowired
    public QuestionService(QuestionRepository questionRepository, WineRepository wineRepository, WineService wineService) {
        this.questionRepository = questionRepository;
        this.wineRepository = wineRepository;
        this.wineService = wineService;
    }

    public ArrayList<QuestionDto> QuestionDtoByCategory(Integer category) {
        ArrayList<Question> questionArrayList = questionRepository.findQuestionByCategory(category);
        ArrayList<QuestionDto> QuestionDtoArrayList = new ArrayList<>();
        for (int i = 0; i < questionArrayList.size(); i++) {
            Question question = questionArrayList.get(i);
            if (question.getAnswerFormat() == 1) {  // likert question
                LikertQuestionDto likertQuestionDto = new LikertQuestionDto(question);
                List<LikertScale> scaleList = questionRepository.findScale(likertQuestionDto.getId());
                for (int j = 0; j < scaleList.size(); j++) {
                    likertQuestionDto.getScaleList().add(scaleList.get(j).getScale());
                }
                QuestionDtoArrayList.add(likertQuestionDto);
            } else if (question.getAnswerFormat() == 2) {   // multiple choice question
                MultipleChoiceQuestionDto multipleChoiceQuestionDto = new MultipleChoiceQuestionDto(question);
                List<QuestionOption> qoList = questionRepository.findByQuestionOption(multipleChoiceQuestionDto.getId());
                ArrayList<QuestionOption> optionList = new ArrayList<>();
                optionList.addAll(qoList);
                System.out.println(optionList.size());
                for (int j = 0; j < optionList.size(); j++) {
                    multipleChoiceQuestionDto.addOption(optionList.get(j).getChoice());
                }

                QuestionDtoArrayList.add(multipleChoiceQuestionDto);
            }
        }

        return QuestionDtoArrayList;
    }

    public WineDto findSimilarWineDto(AnswerDto answerDto) {
        // answerDto parameters
        ArrayList<String> parameters = new ArrayList<>(Arrays.asList("type", "sugar", "food", "flavor",
                                                                    "tannin", "body", "acidity", "alcohol",
                                                                    "variety", "country"));
        // answerDto parameter values
        ArrayList<Integer> parameter_values = new ArrayList<>(Arrays.asList(answerDto.getType(),
                answerDto.getSugar(), answerDto.getFood(), answerDto.getSmell(), answerDto.getTannin(),
                answerDto.getBody(), answerDto.getAcidity(), answerDto.getAlcohol(),
                answerDto.getVariety(), answerDto.getCountry()));

        // make query string
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select m from Wine m where m.sugar = " + parameter_values.get(0));
        String query = stringBuffer.toString();
        String previous = new String();

        List<Wine> wineList = wineRepository.wineListByQuery(query);

        for (int i = 1; i < parameters.size() && !wineList.isEmpty(); i++) {
            previous = stringBuffer.toString();

            stringBuffer.append(" and m." + parameters.get(i) + " = " + parameter_values.get(i));
            query = stringBuffer.toString();
            wineList = wineRepository.wineListByQuery(query);
        }
        Random random = new Random();
        wineList = wineRepository.wineListByQuery(previous);
        int randomInt = random.nextInt(wineList.size());
        return new WineDto(wineList.get(randomInt));
    }

    public WineInfoDto findSimilarWineInfoDto(AnswerDto answerDto) {
        // answerDto parameters
        ArrayList<String> parameters = new ArrayList<>(Arrays.asList("type", "sugar", "food", "flavor",
                                                                    "tannin", "body", "acidity", "alcohol",
                                                                    "variety", "country"));
        // answerDto parameter values
        ArrayList<Integer> parameter_values = new ArrayList<>(Arrays.asList(answerDto.getType(),
                answerDto.getSugar(), answerDto.getFood(), answerDto.getSmell(), answerDto.getTannin(),
                answerDto.getBody(), answerDto.getAcidity(), answerDto.getAlcohol(),
                answerDto.getVariety(), answerDto.getCountry()));

        // make query string
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select m from Wine m where m.sugar = " + parameter_values.get(0));
        String query = stringBuffer.toString();
        String previous = new String();

        List<Wine> wineList = wineRepository.wineListByQuery(query);

        for (int i = 1; i < parameters.size() && !wineList.isEmpty(); i++) {
            previous = stringBuffer.toString();

            stringBuffer.append(" and m." + parameters.get(i) + " = " + parameter_values.get(i));
            query = stringBuffer.toString();
            wineList = wineRepository.wineListByQuery(query);
        }
        Random random = new Random();
        wineList = wineRepository.wineListByQuery(previous);
        int randomInt = random.nextInt(wineList.size());
        Wine result = wineList.get(randomInt);
        return wineService.wineInfoDtoById(result.getId());
    }
}
