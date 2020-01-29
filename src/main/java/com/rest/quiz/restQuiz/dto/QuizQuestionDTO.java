package com.rest.quiz.restQuiz.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestionDTO {
    private Long id;
    private String question;
    private int displayOrder;
    private Long quizId;
}
