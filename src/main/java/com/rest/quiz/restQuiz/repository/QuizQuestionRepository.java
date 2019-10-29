package com.rest.quiz.restQuiz.repository;

import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.model.QuizQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionRepository extends CrudRepository<QuizQuestion, Long> {
}
