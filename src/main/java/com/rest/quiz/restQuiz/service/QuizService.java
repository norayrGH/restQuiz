package com.rest.quiz.restQuiz.service;

import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.dto.QuizQuestionDTO;
import com.rest.quiz.restQuiz.exeption.QuizNotFoundException;
import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.model.QuizQuestion;
import com.rest.quiz.restQuiz.repository.QuizQuestionRepository;
import com.rest.quiz.restQuiz.repository.QuizRepository;
import com.rest.quiz.restQuiz.service.mapper.MapModel;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizQuestionRepository quizQuestionRepository;
    private final MapModel mapModel;
    private final DozerBeanMapper dozerBeanMapper;
    @Autowired
    public QuizService(QuizRepository quizRepository, QuizQuestionRepository quizQuestionRepository, MapModel mapModel, DozerBeanMapper dozerBeanMapper) {
        this.quizRepository = quizRepository;
        this.quizQuestionRepository = quizQuestionRepository;
        this.mapModel = mapModel;
        this.dozerBeanMapper = dozerBeanMapper;
    }
    @Transactional
    public QuizDTO getQuizById(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new QuizNotFoundException("Quiz not Found with " + quizId + " id."));
        return mapModel.convertToDto(quiz);
    }
    public QuizQuestionDTO getQuizQuestionById(Long quizQuestionId){
        QuizQuestion quizQuestion = quizQuestionRepository.findById(quizQuestionId).orElseThrow(() -> new QuizNotFoundException("QuizQuestion not Found with " + quizQuestionId + " id."));
        return mapModel.convertToDto(quizQuestion);
    }
    public List<Quiz> getAllQuizes() {
        List<Quiz> quizzes = (List<Quiz>) quizRepository.findAll();
        if (quizzes.isEmpty())
            throw new QuizNotFoundException("Any quiz not found.");
        return quizzes;
    }
    public Set<QuizQuestion> getAllQuizeQuestions() {
        Set<QuizQuestion> quizzes = (Set<QuizQuestion>) quizQuestionRepository.findAll();
        if (quizzes.isEmpty())
            throw new QuizNotFoundException("Any quiz question not found.");
        return quizzes;
    }

    public void saveQuiz(QuizDTO quizDTO) {
        Quiz quiz = mapModel.convertToEntity(quizDTO);
        quizRepository.save(quiz);
    }

    public void saveQuizQuestion(Long quizId, QuizQuestionDTO quizQuestionDTO) {
        QuizQuestion quizQuestion = mapModel.convertToEntity(quizQuestionDTO);
        quizQuestion.getQuiz().setId(quizId);
        quizQuestionRepository.save(quizQuestion);
    }

    public QuizDTO updateQuiz(QuizDTO quizDTO, long quizId) {
        quizDTO.setId(quizId);
        Quiz quizUpdate = quizRepository.findById(quizId).orElseThrow(() -> new QuizNotFoundException(
                "Quiz not Found with " + quizId + " id.")
        );

        dozerBeanMapper.map(quizDTO, quizUpdate);
        return mapModel.convertToDto(quizRepository.save(quizUpdate));
    }

    public void deleteQuiz(long quizId) {
        quizRepository.deleteById(quizId);
    }

}
