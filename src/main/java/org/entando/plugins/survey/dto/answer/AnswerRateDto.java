package org.entando.plugins.survey.dto.answer;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.entando.plugins.survey.model.answer.Answer;
import org.entando.plugins.survey.model.answer.AnswerRate;
import org.entando.plugins.survey.model.question.QuestionList;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerRateDto extends AnswerDto {

    private int selectedRate;

    @Builder
    public AnswerRateDto(String questionId, int selectedRate) {
        super(questionId);

        this.selectedRate = selectedRate;
    }

    @Override
    public Answer toModel() {
        return AnswerRate.builder()
                .question(new QuestionList(UUID.fromString(questionId)))
                .selectedRate(selectedRate)
                .build();
    }
}
