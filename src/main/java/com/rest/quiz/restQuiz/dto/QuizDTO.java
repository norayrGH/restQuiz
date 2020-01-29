package com.rest.quiz.restQuiz.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {

    private Long id;
    private String quizName;
    private List<QuizQuestionDTO> quizQuestionList;
    private Date startDate;
    private Date endDate;
    private boolean activity;

}
