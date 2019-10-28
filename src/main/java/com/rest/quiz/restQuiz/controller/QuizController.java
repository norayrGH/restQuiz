package com.rest.quiz.restQuiz.controller;

import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.service.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class QuizController {

    @Autowired
    QuizService quizService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/getQuizById")
    ResponseEntity getQuizById(@RequestParam("id") long quizId){
        return ResponseEntity.ok().body(quizService.getQuizById(quizId));
    }

    @PostMapping(value = "/createQuiz")
    ResponseEntity createQuiz(@RequestBody Quiz quiz){
        quizService.saveQuiz(quiz);
        return ResponseEntity.ok().body("Quiz which id: "+quiz.getId()+" hes been saved");
    }

    private QuizDTO convertToDto(Quiz quiz) {
        QuizDTO quizDTO = modelMapper.map(quiz, QuizDTO.class);
        return quizDTO;
    }

    private Quiz convertToEntity(QuizDTO quizDTO) throws ParseException {
        Quiz quiz = modelMapper.map(quizDTO, Quiz.class);

        if (quizDTO.getId() != 0) {
            Quiz oldQuiz = quizService.getQuizById(quizDTO.getId());
            quiz.setId(oldQuiz.getId());
        }
        return quiz;
    }

}
