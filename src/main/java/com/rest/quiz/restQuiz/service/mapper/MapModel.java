package com.rest.quiz.restQuiz.service.mapper;

import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.service.QuizService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.List;

public class MapModel {

    @Autowired
    private static QuizService quizService;

  private static Mapper mapper = new DozerBeanMapper();

    public static QuizDTO convertToDto(Quiz quiz) {
        QuizDTO quizDTO = new QuizDTO();
        quizDTO=mapper.map(quiz, QuizDTO.class);
        return quizDTO;
    }

    public static Quiz convertToEntity(QuizDTO quizDTO){
        Quiz quiz = mapper.map(quizDTO, Quiz.class);
        quiz.getQuizQuestionList().forEach(x->x.setQuiz(quiz));
        if (quizDTO.getId() != 0) {
            Quiz oldQuiz = quizService.getQuizById(quizDTO.getId());
            quiz.setId(oldQuiz.getId());
        }
        return quiz;
    }

}
