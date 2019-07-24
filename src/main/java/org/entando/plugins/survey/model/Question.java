package org.entando.plugins.survey.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.dto.answer.AnswerDto;
import org.entando.plugins.survey.dto.question.QuestionDto;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    discriminatorType = DiscriminatorType.STRING,
    name = "type",
    columnDefinition = "VARCHAR"
)
@Table(name = "questions")
public abstract class Question {
    @Id
    @Column(name = "id")
    protected UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", insertable = false, updatable = false)
    protected QuestionType type;

    @Column(name = "question")
    protected String question;

    @Column(name = "norder")
    protected int order;

    @ManyToOne(optional = false)
    @JoinColumn(name="survey_id")
    protected Survey survey;

    public abstract QuestionDto toDto();

    public enum QuestionType {
        list,
        text,
        rate;
    }

}
