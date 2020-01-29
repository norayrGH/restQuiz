package com.rest.quiz.restQuiz.service.mapper;

import com.rest.quiz.restQuiz.dto.QuizQuestionDTO;
import com.rest.quiz.restQuiz.model.QuizQuestion;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import java.util.List;

public class QuizQuestionDTOConvert extends DozerConverter<List, List> implements MapperAware {


    public QuizQuestionDTOConvert() {
        super(List.class, List.class);
    }

    @Override
    public List convertTo(List source, List destination) {
        return null;
    }

    @Override
    public List convertFrom(List source, List destination) {
        for (int i = 0; i < source.size(); i++) {
            QuizQuestionDTO quizQuestionDTO = (QuizQuestionDTO) source.get(i);
            if (i >= destination.size()) {
                QuizQuestion quizQuestion = (QuizQuestion) destination.get(i - 1);
                destination.add(new QuizQuestion(null, quizQuestion.getQuiz(), quizQuestionDTO.getQuestion(), quizQuestionDTO.getDisplayOrder()));
            } else {
                QuizQuestion quizQuestion = (QuizQuestion) destination.get(i);
                quizQuestion.setQuestion(quizQuestionDTO.getQuestion());
                quizQuestion.setDisplayOrder(quizQuestionDTO.getDisplayOrder());
                destination.set(i, quizQuestion);
            }
        }
        return destination;
    }

    @Override
    public void setMapper(Mapper mapper) {

    }
}