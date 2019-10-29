package com.rest.quiz.restQuiz.controller;

import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.service.QuizService;
import com.rest.quiz.restQuiz.service.mapper.MapModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController  {

    @Autowired
    private QuizService quizService;

    @GetMapping(value = "/quizes/{id}")
    ResponseEntity getQuizById(@PathVariable("id") long quizId){
        /*QuizConvertToDTO quizConvertToDTO = Mappers.getMapper(QuizConvertToDTO.class);*/
        QuizDTO quizDTO = MapModel.convertToDto(quizService.getQuizById(quizId));
        return ResponseEntity.ok().body(quizDTO);
    }

    @PutMapping(value = "/quizes/{id}")
    ResponseEntity updateQuizById(@PathVariable("id") long quizId) {

        return ResponseEntity.ok().build();
    }
    @DeleteMapping(value = "/quizes/{id}")
    ResponseEntity deleteQuizById(@PathVariable("id") long quizId) {

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/quizes")
    ResponseEntity createQuiz(@RequestBody QuizDTO quizDTO){
        try{
            quizService.saveQuiz(quizDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
        return ResponseEntity.ok().body("Quiz which id: "+quizDTO.getId()+" hes been saved.");
    }

}
