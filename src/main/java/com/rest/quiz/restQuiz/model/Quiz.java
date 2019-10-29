package com.rest.quiz.restQuiz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(exclude = "quizQuestionList")
@Entity
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
    private Set<QuizQuestion> quizQuestionList;
    private Date startDate;
    private Date endDate;
    private boolean activity;
}
