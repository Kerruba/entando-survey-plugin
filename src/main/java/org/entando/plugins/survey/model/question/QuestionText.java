package org.entando.plugins.survey.model.question;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.dto.question.QuestionDto;
import org.entando.plugins.survey.dto.question.QuestionListDto;
import org.entando.plugins.survey.dto.question.QuestionTextDto;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.Survey;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("text")
public class QuestionText extends Question {
    @Column(name = "min_length")
    protected int minLength;

    @Column(name = "max_length")
    protected int maxLength;

    @Builder
    public QuestionText(UUID id, String question, int order, Survey survey, int minLength, int maxLength) {
        super(id, QuestionType.text, question, order, survey);

        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public QuestionDto toDto() {
        return QuestionTextDto.builder()
                .id(id.toString())
                .order(order)
                .question(question)
                .minLength(minLength)
                .maxLength(maxLength)
                .build();
    }
}
