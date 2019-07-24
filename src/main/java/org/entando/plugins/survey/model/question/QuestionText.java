package org.entando.plugins.survey.model.question;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.entando.plugins.survey.model.Question;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "question_text")
public class QuestionText extends Question {
    @Column(name = "min_length")
    protected int minLength;

    @Column(name = "max_length")
    protected int maxLength;

    @Builder
    public QuestionText(UUID id, String question, int minLength, int maxLength) {
        super(id, QuestionType.text, question);

        this.minLength = minLength;
        this.maxLength = maxLength;
    }
}
