package org.entando.plugins.survey.model.answer;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.dto.answer.AnswerDto;
import org.entando.plugins.survey.dto.answer.AnswerTextDto;
import org.entando.plugins.survey.model.question.Question;
import org.entando.plugins.survey.model.survey.SurveySubmission;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("text")
public class AnswerText extends Answer {

    @Lob
    @Column(name = "answer_text")
    private String answerText;

    @Builder
    public AnswerText(UUID id, Question question, SurveySubmission submission, String answerText) {
        super(id, Question.QuestionType.text, question, submission);

        this.answerText = answerText;
    }

    @Override
    public AnswerDto toDto() {
        return AnswerTextDto.builder()
                .questionId(getQuestion().getId().toString())
                .answerText(answerText)
                .build();
    }
}
