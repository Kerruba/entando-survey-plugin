package org.entando.plugins.survey.model.question;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.dto.question.QuestionDto;
import org.entando.plugins.survey.dto.question.QuestionListDto;
import org.entando.plugins.survey.dto.question.QuestionRateDto;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.Survey;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("rate")
public class QuestionRate extends Question {
    @Column(name = "min_rate")
    protected int minRate;

    @Column(name = "max_rate")
    protected int maxRate;

    @Builder
    public QuestionRate(UUID id, String question, int order, Survey survey, int minRate, int maxRate) {
        super(id, QuestionType.rate, question, order, survey);

        this.minRate = minRate;
        this.maxRate = maxRate;
    }

    @Override
    public QuestionDto toDto() {
        return QuestionRateDto.builder()
                .id(id.toString())
                .order(order)
                .question(question)
                .minRate(minRate)
                .maxRate(maxRate)
                .build();
    }
}
