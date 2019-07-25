package org.entando.plugins.survey.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.dto.question.QuestionDto;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.Survey;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateSurveyRequest {

    private String id;
    private String title;
    private String description;
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
