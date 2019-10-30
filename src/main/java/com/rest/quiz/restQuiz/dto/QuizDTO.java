package com.rest.quiz.restQuiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizDTO quizDTO = (QuizDTO) o;
        return getId() == quizDTO.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
