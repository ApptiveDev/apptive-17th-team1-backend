package com.example.wineapi.service;

import com.example.wineapi.data.dto.question.AnswerDto;
import com.example.wineapi.data.dto.question.QuestionDto;
import com.example.wineapi.data.dto.wine.WineDto;
import com.example.wineapi.data.entity.question.Question;
import com.example.wineapi.data.entity.question.QuestionOption;
import com.example.wineapi.data.dto.question.MultipleChoiceQuestionDto;
import com.example.wineapi.data.entity.wine.Wine;
import com.example.wineapi.data.repository.wine.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.wineapi.data.repository.QuestionRepository;


import java.util.*;

@Transactional
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final WineRepository wineRepository;


    @Autowired
    public QuestionService(QuestionRepository questionRepository, WineRepository wineRepository) {
        this.questionRepository = questionRepository;
        this.wineRepository = wineRepository;
    }

    public ArrayList<QuestionDto> QuestionDtoByCategory(Integer category) {
        ArrayList<Question> questionArrayList = questionRepository.findQuestionByCategory(category);
        ArrayList<QuestionDto> questionDtoArrayList = new ArrayList<>();
        for (int i = 0; i < questionArrayList.size(); i++) {
            Question question = questionArrayList.get(i);
            if (question.getAnswerFormat() == 1) {
                QuestionDto questionDto = new QuestionDto(question);
                questionDtoArrayList.add(questionDto);
            } else {
                MultipleChoiceQuestionDto questionDto = new MultipleChoiceQuestionDto(question);
                List<QuestionOption> qoList = questionRepository.findByQuestionOption(questionDto.getId());
                ArrayList<QuestionOption> optionList = new ArrayList<>();
                optionList.addAll(qoList);
                System.out.println(optionList.size());
                for (int j = 0; j < optionList.size(); j++) {
                    questionDto.getQuestion_option().add(optionList.get(j).getChoice());
                }

                questionDtoArrayList.add(questionDto);
            }
        }

        return questionDtoArrayList;
    }

    public WineDto findSimilarWineDto(AnswerDto answerDto) {
        // answerDto parameters
        ArrayList<String> parameters = new ArrayList<>(Arrays.asList("sugar", "tannin", "body", "carbonicAcid",
                                                                    "smell", "mood", "pairing", "variety", "country",
                                                                    "type", "alcohol"));
        // answerDto parameter values
        ArrayList<Integer> parameter_values = new ArrayList<>(Arrays.asList(answerDto.getSugar(),
                answerDto.getTannin(), answerDto.getBody(), answerDto.getCarbonicAcid(), answerDto.getSmell(),
                answerDto.getMood(), answerDto.getPairing(), answerDto.getVariety(), answerDto.getCountry(),
                answerDto.getType(), answerDto.getAlcohol()));

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
}
