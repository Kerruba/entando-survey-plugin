package org.entando.plugins.survey.dto.question;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.entando.plugins.survey.model.question.Question;
import org.entando.plugins.survey.model.question.QuestionEnableExpression;
import org.entando.plugins.survey.model.question.QuestionRate;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionRateDto extends QuestionDto {

    private int minRate;
    private int maxRate;

    @Builder
    public QuestionRateDto(String id, String key, String question, int order, QuestionEnableExpressionDto whenExpression, int minRate, int maxRate) {
        super(id, key, question, order, whenExpression);

        this.minRate = minRate;
        this.maxRate = maxRate;
    }

    @Override
    public Question toModel() {
        return QuestionRate.builder()
                .key(key)
                .order(order)
                .question(question)
                .enableExpression(when == null ? null : QuestionEnableExpression.builder()
                        .expression(when.getExpression())
                        .parentKey(when.getQuestionKey())
                        .build())
                .minRate(minRate)
                .maxRate(maxRate)
                .build();
    }
}
