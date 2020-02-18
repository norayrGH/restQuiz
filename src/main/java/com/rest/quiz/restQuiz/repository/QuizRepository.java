package com.rest.quiz.restQuiz.repository;

import com.rest.quiz.restQuiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

     Quiz findTopByOrderByIdDesc();

}
