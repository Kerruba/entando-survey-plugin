package org.entando.plugins.survey.service;

import org.entando.plugins.survey.dto.request.CreateSurveyRequest;
import org.entando.plugins.survey.model.*;
import org.entando.plugins.survey.dto.request.SubmitSurveyRequest;

import java.util.List;

public interface SurveyService {

    Survey get(final String questionnaireId);

    Survey create(final CreateSurveyRequest request);

    SurveySubmission submit(final String uuid, SubmitSurveyRequest request);

    SurveySubmission getSubmission(final String serveyUuid, final String submissionUuid);

    List<SurveySubmission> list(final String uuid, BasePageable page);

}
