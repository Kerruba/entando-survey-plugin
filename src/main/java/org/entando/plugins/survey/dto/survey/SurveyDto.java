package org.entando.plugins.survey.dto.survey;

import lombok.*;
import org.entando.plugins.survey.dto.answer.AnswerDto;
import org.entando.plugins.survey.dto.question.QuestionDto;
import org.entando.plugins.survey.model.Answer;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.Survey;
import org.entando.plugins.survey.model.SurveySubmission;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDto {
    UUID id;
    String title;
    String description;
    @Singular List<QuestionDto> questions;

    public static SurveyDto fromModel(Survey model) {
        SurveyDto.SurveyDtoBuilder builder = SurveyDto.builder()
                .id(model.getId())
                .title(model.getTitle())
                .description(model.getDescription());

        for (Question question: model.getQuestions()) {
            builder.question(question.toDto());
        }

        return builder.build();
    }


}
