package org.entando.plugins.survey.dto.answer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.model.answer.AnswerListOption;
import org.entando.plugins.survey.model.question.QuestionListOption;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerListOptionDto {
    private String selectedKey;

    public AnswerListOptionDto(final AnswerListOption option) {
        this.selectedKey = option.getKey();
    }

}
