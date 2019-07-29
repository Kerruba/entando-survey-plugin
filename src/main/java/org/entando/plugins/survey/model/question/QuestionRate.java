package org.entando.plugins.survey.model.question;

import lombok.*;
import org.entando.plugins.survey.dto.question.QuestionDto;
import org.entando.plugins.survey.dto.question.QuestionEnableExpressionDto;
import org.entando.plugins.survey.dto.question.QuestionRateDto;
import org.entando.plugins.survey.model.survey.Survey;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("rate")
public class QuestionRate extends Question {

    @Column(name = "min_rate")
    private int minRate;

    @Column(name = "max_rate")
    private int maxRate;

    public QuestionRate(final UUID id) {
        super(id);
    }

    @Builder
    public QuestionRate(String question, String key, int order, Survey survey, QuestionEnableExpression enableExpression, int minRate, int maxRate) {
        super(QuestionType.rate, question, key, order, survey, enableExpression);

        this.minRate = minRate;
        this.maxRate = maxRate;
    }

    @Override
    public QuestionDto toDto() {
        return QuestionRateDto.builder()
                .id(getId().toString())
                .key(getKey())
                .order(getOrder())
                .question(getQuestion())
                .whenExpression(enableExpression == null ? null : new QuestionEnableExpressionDto(enableExpression))
                .minRate(minRate)
                .maxRate(maxRate)
                .build();
    }
}
