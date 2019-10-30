package com.rest.quiz.restQuiz.service.mapper;

import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.service.QuizService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MapModel {

    @Autowired
    private static QuizService quizService;

    private static Mapper mapper = new DozerBeanMapper();

    public static QuizDTO convertToDto(Quiz quiz) {
        return mapper.map(quiz, QuizDTO.class);
    }

    public static Quiz convertToEntity(QuizDTO quizDTO) {
        Quiz quiz = mapper.map(quizDTO, Quiz.class);
        quiz.getQuizQuestionList().forEach(x -> x.setQuiz(quiz));
        return quiz;
    }
    public static void mapForUpdate(QuizDTO quizDTO,Quiz quiz){
        mapper.map(quizDTO,quiz);
    }

    public  static Mapper getMapper() {
        return mapper;
    }

    public static void setMapper(Mapper mapper) {
        MapModel.mapper = mapper;
    }
}
