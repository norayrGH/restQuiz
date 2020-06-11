package com.rest.quiz.restQuiz.model;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String quizName;
    @OneToMany(mappedBy = "quiz")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 10)
    private List<QuizQuestion> quizQuestionList = new ArrayList<>();
    private Date startDate;
    private Date endDate;
    @Enumerated(EnumType.STRING)
    private QuizState quizState = QuizState.ACTIVE;


    @PreRemove
    public void deleteUser() {
        this.quizState = QuizState.DELETED;
    }
}
