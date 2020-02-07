package com.rest.quiz.restQuiz.service.mapper;

import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.dto.QuizQuestionDTO;
import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.model.QuizQuestion;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapModel {

    private Mapper dozerMapper = new DozerBeanMapper();

    private final DozerBeanMapper dozerBeanMapper;

    public MapModel(DozerBeanMapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    public QuizDTO convertToDto(Quiz quiz) {
        return dozerMapper.map(quiz, QuizDTO.class);
    }
    public QuizQuestionDTO convertToDto(QuizQuestion quizQuestion) {
        return dozerMapper.map(quizQuestion, QuizQuestionDTO.class);
    }

    public Quiz convertToEntity(QuizDTO quizDTO) {
        Quiz quiz = dozerMapper.map(quizDTO, Quiz.class);
        if(!quiz.getQuizQuestionList().isEmpty()) {
            quiz.getQuizQuestionList().forEach(x -> x.setQuiz(quiz));
        }
        return quiz;
    }

    public QuizQuestion convertToEntity(QuizQuestionDTO quizQuestionDTO) {
        QuizQuestion quizQuestion = dozerMapper.map(quizQuestionDTO, QuizQuestion.class);
        return quizQuestion;
    }

    public void mapForUpdate(QuizQuestionDTO quizDTO, QuizQuestion quiz) {
        dozerMapper.map(quizDTO, quiz);
    }

    public void mapForUpdate(QuizDTO quizDTO, Quiz quiz) {
        dozerBeanMapper.map(quizDTO,quiz);
    }

    public Mapper getDozerMapper() {
        return dozerMapper;
    }
}
