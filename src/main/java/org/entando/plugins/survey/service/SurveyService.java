package org.entando.plugins.survey.service;

import org.entando.plugins.survey.dto.request.CreateSurveyRequest;
import org.entando.plugins.survey.dto.request.SubmitSurveyRequest;
import org.entando.plugins.survey.model.BasePageable;
import org.entando.plugins.survey.model.Survey;
import org.entando.plugins.survey.model.SurveySubmission;

import java.util.List;

public interface SurveyService {

    Survey get(final String questionnaireId);

    Survey create(final CreateSurveyRequest request);

    List<Survey> list(final BasePageable page);

    SurveySubmission submit(final String uuid, SubmitSurveyRequest request);

    SurveySubmission getSubmission(final String serveyUuid, final String submissionUuid);

    List<SurveySubmission> listSubmissions(final String uuid, BasePageable page);

}
