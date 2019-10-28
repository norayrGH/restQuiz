package com.rest.quiz.restQuiz.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String quizName;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "quiz"
    )
    private List<QuizQuestion> quizQuestionList;
    private Date startDate;
    private Date endDate;
    private boolean activity;
}
