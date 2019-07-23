package org.entando.plugins.survey.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.UUID;

@Data
@AllArgsConstructor
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
