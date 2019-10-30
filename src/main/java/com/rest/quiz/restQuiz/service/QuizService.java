package com.rest.quiz.restQuiz.service;

import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.exeption.QuizNotFoundException;
import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.repository.QuizRepository;
import com.rest.quiz.restQuiz.service.mapper.MapModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public QuizDTO getQuizById(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new QuizNotFoundException("Quiz not Found with " + quizId + " id."));
        return MapModel.convertToDto(quiz);
    }

    public Set<Quiz> getAllQuizes() {
        Set<Quiz> quizzes = (Set<Quiz>) quizRepository.findAll();
        if (quizzes.isEmpty())
            return null;
        return quizzes;
    }

    public void saveQuiz(QuizDTO quizDTO) {
        Quiz quiz = MapModel.convertToEntity(quizDTO);
        quizRepository.save(quiz);
    }

    public QuizDTO updateQuiz(QuizDTO quizDTO, long quizId) {
        quizDTO.setId(quizId);
        Quiz quizUpdate = quizRepository.findById(quizId).orElseThrow(() -> new QuizNotFoundException(
                "Quiz not Found with " + quizId + " id.")
        );
        MapModel.mapForUpdate(quizDTO, quizUpdate);
        return MapModel.convertToDto(quizRepository.save(quizUpdate));
    }
}
