package com.rest.quiz.restQuiz.repository;

import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.model.QuizState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

     Quiz findTopByOrderByIdDesc();
     @Query("select q.quizState from #{#entityName} q where q.id = ?1 ")
     QuizState getQuizStateById(Long id);

}
