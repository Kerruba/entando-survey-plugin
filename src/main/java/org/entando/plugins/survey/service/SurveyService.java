package org.entando.plugins.survey.service;

import org.entando.plugins.survey.model.*;

import java.util.Date;
import java.util.List;

public interface SurveyService {

    Survey get(final String questionnaireId);

    SurveySubmission submit(final String uuid, final Date date, final List<AnswerQuestion> answers);

    SurveySubmission getSubmission(final String uuid, final String submissionUuid);

    BasePage<SurveySubmission> list(final String uuid, BasePageable page);

}
