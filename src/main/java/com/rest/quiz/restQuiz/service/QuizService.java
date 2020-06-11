package com.rest.quiz.restQuiz.service;

import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.dto.QuizQuestionDTO;
import com.rest.quiz.restQuiz.exeption.QuizNotFoundException;
import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.model.QuizQuestion;
import com.rest.quiz.restQuiz.model.QuizState;
import com.rest.quiz.restQuiz.repository.QuizQuestionRepository;
import com.rest.quiz.restQuiz.repository.QuizRepository;
import com.rest.quiz.restQuiz.service.mapper.MapModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizQuestionRepository quizQuestionRepository;
    private final MapModel mapModel;
    @Transactional
    public QuizDTO getQuizById(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new QuizNotFoundException("Quiz not Found with " + quizId + " id."));
        return mapModel.convertToDto(quiz);
    }

    @Transactional
    public QuizQuestionDTO getQuizQuestionById(Long quizQuestionId){
        QuizQuestion quizQuestion = quizQuestionRepository.findById(quizQuestionId).orElseThrow(() -> new QuizNotFoundException("QuizQuestion not Found with " + quizQuestionId + " id."));
        return mapModel.convertToDto(quizQuestion);
    }

    @Transactional
    public List<QuizDTO> getAllQuizes() {
        List<Quiz> quizzes = quizRepository.findAll();
        if (quizzes.isEmpty())
            throw new QuizNotFoundException("Any quiz not found.");
        return quizzes.stream()
                .map(mapModel::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Set<QuizQuestion> getAllQuizeQuestions() {
        Set<QuizQuestion> quizzes = (Set<QuizQuestion>) quizQuestionRepository.findAll();
        if (quizzes.isEmpty())
            throw new QuizNotFoundException("Any quiz question not found.");
        return quizzes;
    }

    public QuizDTO saveQuiz(QuizDTO quizDTO) {
        Quiz quiz = mapModel.convertToEntity(quizDTO);
        return mapModel.convertToDto(quizRepository.save(quiz));
    }

    @Transactional
    public void saveQuizQuestion(Long quizId, QuizQuestionDTO quizQuestionDTO) {
        QuizQuestion quizQuestion = mapModel.convertToEntity(quizQuestionDTO);
        quizQuestion.getQuiz().setId(quizId);
        quizQuestionRepository.save(quizQuestion);
    }

    @Transactional
    public QuizDTO updateQuiz(QuizDTO quizDTO, long quizId) {
        quizDTO.setId(quizId);
        Quiz quizUpdate = quizRepository.findById(quizId).orElseThrow(() -> new QuizNotFoundException(
                "Quiz not Found with " + quizId + " id.")
        );
        Quiz quizForUpdate = mapModel.convertToEntity(quizDTO);
        mapModel.mapForUpdate(quizForUpdate,quizUpdate);
        return mapModel.convertToDto(quizRepository.save(quizUpdate));
    }

    @Transactional
        public QuizQuestionDTO updateQuizQuestion(QuizQuestionDTO quizQuestionDTO, long quizId) {
        quizQuestionDTO.setId(quizId);
        QuizQuestion quizUpdate = quizQuestionRepository.findById(quizId).orElseThrow(() -> new QuizNotFoundException(
                "QuizQuestion not Found with " + quizId + " id.")
        );
        QuizQuestion quizForUpdate = mapModel.convertToEntity(quizQuestionDTO);
        mapModel.mapForUpdate(quizForUpdate,quizUpdate);
        return mapModel.convertToDto(quizQuestionRepository.save(quizUpdate));
    }

    public void deleteQuiz(long quizId) {
        quizRepository.deleteById(quizId);
    }

    @Transactional
    public QuizDTO getQuizStateById(long quizId, String quizState){
        return mapModel.convertToDto(quizRepository.getQuizStateById(quizId, QuizState.valueOf(quizState)));
    }
    @Transactional
    public Long findLastId(){
        return quizRepository.findTopByOrderByIdDesc().getId();
    }

}
