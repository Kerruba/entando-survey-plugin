package org.entando.plugins.survey.service;

import org.entando.plugins.survey.model.Survey;

public interface SurveyService {

    Survey get(final String questionnaireId);

}
