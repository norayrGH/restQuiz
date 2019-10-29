package com.rest.quiz.restQuiz;

import com.rest.quiz.restQuiz.model.QuizQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends CrudRepository<PostComment, Long> {
}
