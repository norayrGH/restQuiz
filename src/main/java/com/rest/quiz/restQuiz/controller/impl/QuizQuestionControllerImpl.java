package com.rest.quiz.restQuiz.controller.impl;

import com.rest.quiz.restQuiz.controller.QuizQuestionController;
import com.rest.quiz.restQuiz.dto.QuizQuestionDTO;
import com.rest.quiz.restQuiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/quizes/{quizeId}/question/")
public class QuizQuestionControllerImpl implements QuizQuestionController {

    private final QuizService quizService;

    @Autowired
    public QuizQuestionControllerImpl(QuizService quizService) {
        this.quizService = quizService;
    }


    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable("id")long quizQuestionId) {
        return ResponseEntity.ok().body(quizService.getQuizQuestionById(quizQuestionId));
    }

    @Override
    public ResponseEntity updateById(QuizQuestionDTO quizQuestionDTO, long quizQuestionId) {
        return null;
    }

    @Override
    public ResponseEntity deleteById(long quizQuestionId) {
        return null;
    }

    @Override
    @PostMapping(value = "")
    public ResponseEntity create(@PathVariable("quizeId")Long quizId, QuizQuestionDTO quizQuestionDTO) {
        quizService.saveQuizQuestion(quizId, quizQuestionDTO);
        return ResponseEntity.ok().body("Quiz question hes been saved.");
    }

    @Override
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(quizService.getAllQuizeQuestions());
    }
}
