package org.entando.plugins.survey.model.question;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.dto.question.QuestionDto;
import org.entando.plugins.survey.dto.question.QuestionEnableExpressionDto;
import org.entando.plugins.survey.dto.question.QuestionTextDto;
import org.entando.plugins.survey.model.survey.Survey;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("text")
public class QuestionText extends Question {

    @Column(name = "min_length")
    private int minLength;

    @Column(name = "max_length")
    private int maxLength;

    public QuestionText(final UUID id) {
        super(id);
    }

    @Builder
    public QuestionText(String question, String key, int order, Survey survey, QuestionEnableExpression enableExpression, int minLength, int maxLength) {
        super(QuestionType.text, question, key, order, survey, enableExpression);

        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public QuestionDto toDto() {
        return QuestionTextDto.builder()
                .id(getId().toString())
                .order(getOrder())
                .question(getQuestion())
                .whenExpression(enableExpression == null ? null : new QuestionEnableExpressionDto(enableExpression))
                .minLength(minLength)
                .maxLength(maxLength)
                .build();
    }
}
