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
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", insertable = false, updatable = false)
    private QuestionType type;

    @Column(name = "question")
    private String question;

    @Column(name = "norder")
    private int order;

    @ManyToOne(optional = false)
    @JoinColumn(name="survey_id")
    private Survey survey;

    public abstract QuestionDto toDto();

    @PrePersist
    public void setUuid() {
        this.id = UUID.randomUUID();
    }

    public enum QuestionType {
        list,
        text,
        rate
    }

}
