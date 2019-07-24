package org.entando.plugins.survey.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.entando.plugins.survey.model.answer.AnswerQuestionList;
import org.entando.plugins.survey.model.answer.AnswerQuestionRate;
import org.entando.plugins.survey.model.answer.AnswerQuestionText;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SubmitSurveyRequest {

    List<AnswerQuestionList> listAnswers = new ArrayList<>();
    List<AnswerQuestionText> textAnswers = new ArrayList<>();
    List<AnswerQuestionRate> rateAnswers = new ArrayList<>();

}
