package org.entando.plugins.survey.model.question;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.dto.question.QuestionDto;
import org.entando.plugins.survey.dto.question.QuestionTextDto;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.Survey;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("text")
public class QuestionText extends Question {

    @Column(name = "min_length")
    private int minLength;

    @Column(name = "max_length")
    private int maxLength;

    @Builder
    public QuestionText(UUID id, String question, int order, Survey survey, int minLength, int maxLength) {
        super(id, QuestionType.text, question, order, survey);

        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public QuestionDto toDto() {
        return QuestionTextDto.builder()
                .id(getId().toString())
                .order(getOrder())
                .question(getQuestion())
                .minLength(minLength)
                .maxLength(maxLength)
                .build();
    }
}
