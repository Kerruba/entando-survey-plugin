package org.entando.plugins.survey.dto.submission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.entando.plugins.survey.dto.answer.AnswerDto;
import org.entando.plugins.survey.dto.survey.SurveyDto;
import org.entando.plugins.survey.model.Answer;
import org.entando.plugins.survey.model.SurveySubmission;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveySubmissionDto {

    private UUID surveyId;
    private UUID submissionId;
    private Date submissionDate;
    private SurveyDto survey;

    @Singular List<AnswerDto> answers;

    public static SurveySubmissionDto fromModel(SurveySubmission model) {
        return SurveySubmissionDto.builder()
                .survey(SurveyDto.fromModel(model.getSurvey()))
                .surveyId(model.getSurvey().getId())
                .submissionId(model.getId())
                .submissionDate(model.getSubmissionDate())
                .answers(model.getAnswers().stream().map(Answer::toDto).collect(Collectors.toList()))
                .build();
    }

}
