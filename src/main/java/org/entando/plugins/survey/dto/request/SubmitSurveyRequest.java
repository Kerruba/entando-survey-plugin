package org.entando.plugins.survey.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.entando.plugins.survey.dto.answer.AnswerDto;
import org.entando.plugins.survey.model.answer.Answer;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class SubmitSurveyRequest {

    @Valid
    @NotNull
    @Size(min = 1)
    private List<AnswerDto> answers = new ArrayList<>();

    public List<Answer> getModel() {
        return answers.stream().map(AnswerDto::toModel).collect(Collectors.toList());
    }

}
