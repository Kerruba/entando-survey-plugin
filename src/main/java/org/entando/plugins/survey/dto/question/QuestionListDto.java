package org.entando.plugins.survey.dto.question;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import org.entando.plugins.survey.model.question.Question;
import org.entando.plugins.survey.model.question.QuestionEnableExpression;
import org.entando.plugins.survey.model.question.QuestionList;
import org.entando.plugins.survey.model.question.QuestionListOption;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionListDto extends QuestionDto {

    private List<QuestionListOptionDto> options;
    private boolean multipleChoice;

    @Builder
    public QuestionListDto(String id, String key, String question, int order,
                           @Singular List<QuestionListOptionDto> options, QuestionEnableExpressionDto whenExpression,
                           boolean multipleChoice) {
        super(id, key, question, order, whenExpression);

        this.options = options;
        this.multipleChoice = multipleChoice;
    }

    @Override
    public Question toModel() {
        UUID questionId = UUID.randomUUID();
        QuestionList.QuestionListBuilder builder = QuestionList.builder()
                .id(questionId)
                .key(key)
                .order(order)
                .question(question)
                .enableExpression(when == null ? null : QuestionEnableExpression.builder()
                        .expression(when.getExpression())
                        .parentKey(when.getQuestionKey())
                        .build())
                .multipleChoice(multipleChoice);

        for(QuestionListOptionDto dto : options) {
            builder.option(QuestionListOption.builder()
                    .key(dto.getKey())
                    .label(dto.getLabel())
                    .question(new QuestionList(questionId))
                    .build());
        }

        return builder.build();
    }
}
