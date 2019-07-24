package org.entando.plugins.survey.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "questions")
public abstract class Question {
    @Id
    @Column(name = "id")
    protected UUID id;

    @Column(name = "type")
    protected QuestionType type;

    @Column(name = "question")
    protected String question;

    public enum QuestionType {
        list,
        text,
        rate;
    }

}
