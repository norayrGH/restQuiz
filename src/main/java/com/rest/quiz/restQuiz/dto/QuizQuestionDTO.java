package com.rest.quiz.restQuiz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestionDTO {

    private Long id;
    private String question;
    private int displayOrder;
    @JsonIgnore
    private Long quizId;

}
