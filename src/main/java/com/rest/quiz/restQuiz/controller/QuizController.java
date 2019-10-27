package com.rest.quiz.restQuiz.controller;

import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping(value = "/getQuizById")
    ResponseEntity getQuizById(@RequestParam("id") long quizId){
        return ResponseEntity.ok().body(quizService.getQuizById(quizId));
    }

    @PostMapping(value = "/createQuiz")
    ResponseEntity createQuiz(@RequestBody Quiz quiz){
        quizService.saveQuiz(quiz);
        return ResponseEntity.ok().body("Quiz which id: "+quiz.getId()+" hes been saved");
    }

}
