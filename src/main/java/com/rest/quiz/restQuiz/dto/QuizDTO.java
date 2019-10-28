package com.rest.quiz.restQuiz.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
public class QuizDTO {

    private long id;
    private String quizName;
    private List<QuizQuestionDTO> quizQuestionList;
    private Date startDate;
    private Date endDate;
    private boolean activity;

}
