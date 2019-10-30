package com.rest.quiz.restQuiz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface QuizController {
    @GetMapping(value = "/quizes/{id}")
    ResponseEntity getQuizById(@PathVariable("id") long quizId);
}
