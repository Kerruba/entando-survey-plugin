package org.entando.plugins.survey.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.question.QuestionList;
import org.entando.plugins.survey.model.question.QuestionListOption;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionListOptionDto {
    String key;
    String label;
}
