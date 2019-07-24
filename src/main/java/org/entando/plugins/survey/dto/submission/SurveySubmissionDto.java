package org.entando.plugins.survey.dto.submission;

import lombok.*;
import org.entando.plugins.survey.dto.answer.AnswerDto;
import org.entando.plugins.survey.model.Answer;
import org.entando.plugins.survey.model.SurveySubmission;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveySubmissionDto {
    UUID surveyId;
    UUID submissionId;
    Date submissionDate;
    @Singular List<AnswerDto> answers;

    public static SurveySubmissionDto fromModel(SurveySubmission model) {
        List<AnswerDto> answers = new ArrayList<>();
        for (Answer answer : model.getAnswers()) {
            answers.add(answer.toDto());
        }

        return SurveySubmissionDto.builder()
                .surveyId(model.getSurvey().getId())
                .submissionId(model.getId())
                .submissionDate(model.getSubmissionDate())
                .answers(answers)
                .build();
    }


}
