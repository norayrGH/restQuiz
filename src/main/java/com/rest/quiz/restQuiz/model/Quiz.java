package com.rest.quiz.restQuiz.model;

import lombok.*;

import javax.persistence.*;
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
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "quiz",
            fetch = FetchType.LAZY
    )
    private List<QuizQuestion> quizQuestionList;
    private Date startDate;
    private Date endDate;
    private boolean activity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return getId() == quiz.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
