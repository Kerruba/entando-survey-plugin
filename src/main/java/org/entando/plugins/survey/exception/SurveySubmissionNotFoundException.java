package org.entando.plugins.survey.exception;

import org.entando.web.exception.NotFoundException;

public class SurveySubmissionNotFoundException extends NotFoundException {

    public SurveySubmissionNotFoundException() {
        super("org.entando.error.surveySubmissionNotFound");
    }

}
