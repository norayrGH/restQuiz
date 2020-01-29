package com.rest.quiz.restQuiz.controller;

import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity getQuizById(@PathVariable("id") long quizId) {
        return ResponseEntity.ok().body(quizService.getQuizById(quizId));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateQuizById(@RequestBody QuizDTO quizDTO, @PathVariable("id") long quizId) {
        return ResponseEntity.ok().body(quizService.updateQuiz(quizDTO, quizId));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteQuizById(@PathVariable("id") long quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseEntity.ok().body("Quiz which id: " + quizId + " hes been deleted.");
    }

    @PostMapping(value = "")
    public ResponseEntity createQuiz(@RequestBody QuizDTO quizDTO) {
        quizService.saveQuiz(quizDTO);
        return ResponseEntity.ok().body("Quiz which id: " + quizDTO.getId() + " hes been saved.");
    }

    @GetMapping(value = "")
    public ResponseEntity getAllQuiz() {
        return ResponseEntity.ok().body(quizService.getAllQuizes());
    }

}
