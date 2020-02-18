package com.rest.quiz.restQuiz.controller;

import com.rest.quiz.restQuiz.dto.QuizDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface QuizController {

    @GetMapping(value = "")
    ResponseEntity getById(@PathVariable("id") long quizId);

    @PutMapping(value = "/{id}")
    ResponseEntity updateById(@RequestBody QuizDTO quizDTO, @PathVariable("id") long quizId);

    @DeleteMapping(value = "/{id}")
    ResponseEntity deleteById(@PathVariable("id") long quizId);

    @PostMapping(value = "")
    ResponseEntity create(@RequestBody QuizDTO quizDTO);

    @GetMapping(value = "")
    ResponseEntity getAll();

    @GetMapping(value = "/last")
    ResponseEntity getLast();
}
