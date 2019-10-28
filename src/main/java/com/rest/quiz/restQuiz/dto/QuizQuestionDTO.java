package com.rest.quiz.restQuiz.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizQuestionDTO {
    private long id;
    private long quizId;
    private String question;
    private int displayOrder;
}
