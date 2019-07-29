package org.entando.plugins.survey.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.dto.question.QuestionDto;
import org.entando.plugins.survey.model.question.Question;
import org.entando.plugins.survey.model.survey.Survey;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateSurveyRequest {

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotNull
    @Size(min = 1)
    private List<QuestionDto> questions = new ArrayList<>();

    public Survey getModel() {
        UUID surveyId = UUID.randomUUID();
        Survey.SurveyBuilder builder = Survey.builder()
                .id(surveyId)
                .title(title)
                .description(description);

        int order = 0;
        for(QuestionDto dto: questions) {
            Question question = dto.toModel();
            question.setSurvey(Survey.builder().id(surveyId).build());
            question.setOrder(order++);

            builder.question(question);
        }

        return builder.build();
    }

}
