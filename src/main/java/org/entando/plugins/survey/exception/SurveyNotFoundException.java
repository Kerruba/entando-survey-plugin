package org.entando.plugins.survey.exception;

import org.entando.web.exception.NotFoundException;

public class SurveyNotFoundException extends NotFoundException {

    public SurveyNotFoundException() {
        super("org.entando.error.surveyNotFound");
    }

}
