package com.rest.quiz.restQuiz.model;

import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE {h-schema}quiz SET quiz_state = 'DELETED', quiz_id = null FROM {h-schema}quiz_question WHERE {h-schema}quiz.id = ? AND {h-schema}quiz.id = {h-schema}quiz_question.quiz_id ", check = ResultCheckStyle.COUNT)
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String quizName;
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "quiz_and_question",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<QuizQuestion> quizQuestionList;
    private Date startDate;
    private Date endDate;
    @Enumerated(EnumType.STRING)
    private QuizState quizState = QuizState.ACTIVE;

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

    @PreRemove
    public void deleteUser() {
        this.quizState = QuizState.DELETED;
    }
}
