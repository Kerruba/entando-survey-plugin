package org.entando.plugins.survey.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.model.question.QuestionListOption;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionListOptionDto {
    private String key;
    private String label;

    public QuestionListOptionDto(final QuestionListOption option) {
        this.key = option.getKey();
        this.label = option.getLabel();
    }

}
