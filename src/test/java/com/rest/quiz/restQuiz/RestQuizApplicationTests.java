package com.rest.quiz.restQuiz;

import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.model.QuizQuestion;
import com.rest.quiz.restQuiz.repository.QuizRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class RestQuizApplicationTests {

	@Autowired
	QuizRepository quizRepository;
	@Autowired
	PostRepository postRepository;
	@Test
	void contextLoads() {

	/*	Post post = new Post();
		post.setTitle("High-Performance Java Persistence");

		PostComment comment = new PostComment();
		comment.setReview("JPA and Hibernate");
		post.addComment(comment);

		postRepository.save(post);*/

		/*quiz.setActivity(true);
		quiz.setQuizName("test");
		quiz.setStartDate(new Date());

		List<QuizQuestion> quizQuestions = new ArrayList<>();
		QuizQuestion quizQuestion = new QuizQuestion();
		quizQuestion.setQuestion("test1");
		quizQuestion.setDisplayOrder(1);
		//quizQuestion.setQuiz(quiz);
		QuizQuestion quizQuestion2 = new QuizQuestion();
		quizQuestion2.setQuestion("test2");
		quizQuestion2.setDisplayOrder(2);
		//quizQuestion2.setQuiz(quiz);


		quizRepository.save(quiz);
*/

	}

}
