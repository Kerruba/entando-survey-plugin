package org.entando.plugins.survey.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.dto.answer.AnswerDto;

import javax.persistence.*;
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
    protected UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", insertable = false, updatable = false)
    protected Question.QuestionType type;

    @ManyToOne(optional = false)
    @JoinColumn(name="question_id")
    protected Question question;

    @ManyToOne(optional = false)
    @JoinColumn(name="submission_id")
    protected SurveySubmission submission;

    public abstract AnswerDto toDto();

}
