package com.rest.quiz.restQuiz.controller.impl;

import com.rest.quiz.restQuiz.controller.QuizController;
import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.service.QuizService;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

@RestController
@RequestMapping(value = "/quizes")
public class QuizControllerImpl implements QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizControllerImpl(QuizService quizService) {
        this.quizService = quizService;
    }


    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable("id") long quizId) {
        return ResponseEntity.ok().body(quizService.getQuizById(quizId));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity updateById(@RequestBody QuizDTO quizDTO, @PathVariable("id") long quizId) {
        return ResponseEntity.ok().body(quizService.updateQuiz(quizDTO, quizId));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteById(@PathVariable("id") long quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseEntity.ok().body("Quiz which id: " + quizId + " hes been deleted.");
    }

    @Override
    @PostMapping(value = "")
    public ResponseEntity create(@RequestBody QuizDTO quizDTO) {
         quizService.saveQuiz(quizDTO);
        return ResponseEntity.ok().body("Quiz which id: " + quizDTO.getId() + " hes been saved.");
    }

    @Override
    @GetMapping(value = "")
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(quizService.getAllQuizes());
    }

}
