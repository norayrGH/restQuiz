package com.rest.quiz.restQuiz.repository;

import com.rest.quiz.restQuiz.model.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends CrudRepository<Quiz, Long> {
}
