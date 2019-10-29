package com.rest.quiz.restQuiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {

    private long id;
    private String quizName;
    private List<QuizQuestionDTO> quizQuestionList;
    private Date startDate;
    private Date endDate;
    private boolean activity;

}
