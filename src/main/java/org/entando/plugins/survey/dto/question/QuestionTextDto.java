package org.entando.plugins.survey.dto.question;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.question.QuestionText;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionTextDto extends QuestionDto {

    private int minLength;
    private int maxLength;

    @Builder
    public QuestionTextDto(String id, String question, int order, int minLength, int maxLength) {
        super(id, question, order);

        this.minLength = minLength;
        this.maxLength = maxLength;
    }


    @Override
    public Question toModel() {
        return QuestionText.builder()
                .order(order)
                .question(question)
                .minLength(minLength)
                .maxLength(maxLength)
                .build();
    }
}
