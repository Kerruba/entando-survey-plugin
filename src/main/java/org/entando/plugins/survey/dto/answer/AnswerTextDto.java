package org.entando.plugins.survey.dto.answer;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.entando.plugins.survey.model.Answer;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.answer.AnswerText;
import org.entando.plugins.survey.model.question.QuestionList;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerTextDto extends AnswerDto {

    @NotEmpty
    private String answerText;

    @Builder
    public AnswerTextDto(String questionId, String answerText) {
        super(questionId, Question.QuestionType.list);
        this.answerText = answerText;
    }

    @Override
    public Answer toModel() {
        return AnswerText.builder()
                .question(new QuestionList(UUID.fromString(questionId)))
                .answerText(answerText)
                .build();
    }
}
