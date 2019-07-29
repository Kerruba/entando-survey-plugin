package org.entando.plugins.survey.dto.submission;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.entando.plugins.survey.dto.answer.AnswerDto;
import org.entando.plugins.survey.dto.survey.SurveyDto;
import org.entando.plugins.survey.model.answer.Answer;
import org.entando.plugins.survey.model.survey.SurveySubmission;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
