package org.entando.plugins.survey.dto.answer;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.entando.plugins.survey.model.answer.Answer;
import org.entando.plugins.survey.model.answer.AnswerList;
import org.entando.plugins.survey.model.answer.AnswerListOption;
import org.entando.plugins.survey.model.question.QuestionList;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerListDto extends AnswerDto {

    @NotNull
    private List<String> selectedKeys;

    @Builder
    public AnswerListDto(String questionId, List<String> selectedKeys) {
        super(questionId);

        this.selectedKeys = selectedKeys;
    }

    @Override
    public Answer toModel() {
        AnswerList model = AnswerList.builder()
                .question(new QuestionList(UUID.fromString(questionId)))
                .build();

        model.setSelectedOptions(selectedKeys.stream()
                .map(key -> AnswerListOption.builder().key(key).answer(model).build())
                .collect(Collectors.toList()));

        return model;
    }
}
