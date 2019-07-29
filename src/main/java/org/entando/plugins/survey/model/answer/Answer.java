package org.entando.plugins.survey.model.answer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.dto.answer.AnswerDto;
import org.entando.plugins.survey.model.survey.SurveySubmission;
import org.entando.plugins.survey.model.question.Question;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "answers")
@DiscriminatorColumn(
    discriminatorType = DiscriminatorType.STRING,
    name = "type",
    columnDefinition = "VARCHAR"
)
public abstract class Answer {

    @Id
    @Column(name = "id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", insertable = false, updatable = false)
    private Question.QuestionType type;

    @ManyToOne(optional = false)
    @JoinColumn(name="question_id")
    private Question question;

    @ManyToOne(optional = false)
    @JoinColumn(name="submission_id")
    private SurveySubmission submission;

    public abstract AnswerDto toDto();

    @PrePersist
    public void setUuid() {
        this.id = UUID.randomUUID();
    }

}
