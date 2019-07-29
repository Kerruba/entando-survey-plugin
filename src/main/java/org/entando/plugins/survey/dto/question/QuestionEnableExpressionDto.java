package org.entando.plugins.survey.dto.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.model.question.QuestionEnableExpression;
import org.entando.plugins.survey.model.question.QuestionListOption;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class QuestionEnableExpressionDto {
    @JsonProperty("question")
    private String questionKey;

    @JsonProperty("response")
    private String expression;

    public QuestionEnableExpressionDto(final QuestionEnableExpression expression) {
        this.questionKey = expression.getParentKey();
        this.expression = expression.getExpression();
    }

}
