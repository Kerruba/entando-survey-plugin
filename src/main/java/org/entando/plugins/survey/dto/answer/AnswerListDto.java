package org.entando.plugins.survey.dto.answer;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.entando.plugins.survey.model.Answer;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.answer.AnswerList;
import org.entando.plugins.survey.model.question.QuestionList;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerListDto extends AnswerDto {

    private List<String> selectedKeys;

    @Builder
    public AnswerListDto(String questionId, List<String> selectedKeys) {
        super(questionId, Question.QuestionType.list);

        this.selectedKeys = selectedKeys;
    }

    @Override
    public Answer toModel() {
        return AnswerList.builder()
                .question(new QuestionList(UUID.fromString(questionId)))
                .selectedKeys(selectedKeys)
                .build();
    }
}
