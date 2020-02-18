package com.rest.quiz.restQuiz.model;

import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "UPDATE TESTSCHEMA.quiz SET quiz_state = 'DELETED' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "quiz_state <> 'DELETED'")
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
    private List<QuizQuestion> quizQuestionList = new ArrayList<>();
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
