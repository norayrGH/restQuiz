package com.rest.quiz.restQuiz.service.mapper;

import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.dto.QuizQuestionDTO;
import com.rest.quiz.restQuiz.model.Quiz;

import com.rest.quiz.restQuiz.model.QuizQuestion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class MapModel {

    private final ModelMapper modelMapper;


    public MapModel(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public QuizDTO convertToDto(Quiz quiz) {
        return modelMapper.map(quiz, QuizDTO.class);
    }
    public QuizQuestionDTO convertToDto(QuizQuestion quizQuestion) {
        return modelMapper.map(quizQuestion, QuizQuestionDTO.class);
    }

    public Quiz convertToEntity(QuizDTO quizDTO) {
        Quiz quiz = modelMapper.map(quizDTO, Quiz.class);
        if(Objects.nonNull(quiz) && Objects.nonNull(quiz.getQuizQuestionList()) && !quiz.getQuizQuestionList().isEmpty()) {
            quiz.getQuizQuestionList().forEach(x -> x.setQuiz(quiz));
        }
        return quiz;
    }

    public QuizQuestion convertToEntity(QuizQuestionDTO quizQuestionDTO) {
        return modelMapper.map(quizQuestionDTO, QuizQuestion.class);
    }

    public void mapForUpdate(QuizQuestion quizForUpdate, QuizQuestion quiz) {
        modelMapper.map(quizForUpdate, quiz);
    }

    public void mapForUpdate(Quiz quizDto, Quiz quiz) {
        modelMapper.map(quizDto,quiz);
    }

}
