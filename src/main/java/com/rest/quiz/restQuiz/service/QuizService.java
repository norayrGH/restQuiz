package com.rest.quiz.restQuiz.service;

import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    public Quiz getQuizById(Long quizId){
       Optional quizOptional = quizRepository.findById(quizId);
       if(!quizOptional.isPresent())
           return null;
        return (Quiz) quizOptional.get();
    }

    public void saveQuiz(Quiz quiz){
        quizRepository.save(quiz);
    }
}
