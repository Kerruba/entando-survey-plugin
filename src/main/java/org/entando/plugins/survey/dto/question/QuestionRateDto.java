package org.entando.plugins.survey.dto.question;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.question.QuestionRate;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionRateDto extends QuestionDto {

    private int minRate;
    private int maxRate;

    @Builder
    public QuestionRateDto(String id, String question, int order, int minRate, int maxRate) {
        super(id, question, order);

        this.minRate = minRate;
        this.maxRate = maxRate;
    }

    @Override
    public Question toModel() {
        return QuestionRate.builder()
                .order(order)
                .question(question)
                .minRate(minRate)
                .maxRate(maxRate)
                .build();
    }
}
