package org.entando.plugins.survey.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.entando.plugins.survey.model.Answer;
import org.entando.plugins.survey.dto.answer.AnswerDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SubmitSurveyRequest {

    List<AnswerDto> answers = new ArrayList<>();

    public List<Answer> getModel() {
        List<Answer> model = new ArrayList<>();
        for(AnswerDto dto : answers) {
            model.add(dto.toModel());
        }
        return model;
    }

}
