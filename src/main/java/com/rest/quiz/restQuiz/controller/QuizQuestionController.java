package com.rest.quiz.restQuiz.controller;

import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.dto.QuizQuestionDTO;
import com.rest.quiz.restQuiz.model.QuizQuestion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface QuizQuestionController {

    @GetMapping(value = "")
    ResponseEntity getById(@PathVariable("id") long quizQuestionId);

    @PutMapping(value = "/{id}")
    ResponseEntity updateById(@RequestBody QuizQuestionDTO quizQuestionDTO, @PathVariable("id") long quizQuestionId);

    @DeleteMapping(value = "/{id}")
    ResponseEntity deleteById(@PathVariable("id") long quizQuestionId);

    @PostMapping(value = "")
    ResponseEntity create(@PathVariable("quizeId") Long quizId, QuizQuestionDTO quizQuestionDTO);

    @GetMapping(value = "")
    ResponseEntity getAll();
}
