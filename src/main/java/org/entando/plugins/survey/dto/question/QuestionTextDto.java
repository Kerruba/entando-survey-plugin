package org.entando.plugins.survey.dto.question;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.entando.plugins.survey.model.question.Question;
import org.entando.plugins.survey.model.question.QuestionEnableExpression;
import org.entando.plugins.survey.model.question.QuestionText;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionTextDto extends QuestionDto {

    private int minLength;
    private int maxLength;

    @Builder
    public QuestionTextDto(String id, String key, String question, int order, QuestionEnableExpressionDto whenExpression,
                           int minLength, int maxLength) {
        super(id, key, question, order, whenExpression);

        this.minLength = minLength;
        this.maxLength = maxLength;
    }


    @Override
    public Question toModel() {
        return QuestionText.builder()
                .key(key)
                .order(order)
                .question(question)
                .enableExpression(when == null ? null : QuestionEnableExpression.builder()
                        .expression(when.getExpression())
                        .parentKey(when.getQuestionKey())
                        .build())
                .minLength(minLength)
                .maxLength(maxLength)
                .build();
    }
}
