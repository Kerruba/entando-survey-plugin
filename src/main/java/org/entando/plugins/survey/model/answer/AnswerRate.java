package org.entando.plugins.survey.model.answer;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.dto.answer.AnswerDto;
import org.entando.plugins.survey.dto.answer.AnswerRateDto;
import org.entando.plugins.survey.model.Answer;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.SurveySubmission;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("rate")
public class AnswerRate extends Answer {

    @Column(name = "selected_rate")
    private int selectedRate;

    @Builder
    public AnswerRate(UUID id, Question question, SurveySubmission submission, int selectedRate) {
        super(id, Question.QuestionType.rate, question, submission);

        this.selectedRate = selectedRate;
    }

    @Override
    public AnswerDto toDto() {
        return AnswerRateDto.builder()
                .questionId(getQuestion().getId().toString())
                .selectedRate(selectedRate)
                .build();
    }
}
