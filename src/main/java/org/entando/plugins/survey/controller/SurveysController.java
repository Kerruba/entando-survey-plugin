package org.entando.plugins.survey.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.entando.plugins.survey.model.Survey;
import org.entando.plugins.survey.service.SurveyService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.entando.plugins.survey.controller.AuthPermissions.SURVEY_GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@Api(tags = "Surveys")
@RequestMapping(path = "/surveys")
@RequiredArgsConstructor
public class SurveysController {

    private final @NonNull
    SurveyService surveyService;

    @Secured(SURVEY_GET)
    @ApiOperation(notes = "Gets a survey", nickname = "getSurvey", value = "Get a Survey")
    @GetMapping(path = "/{uuid}", produces = { APPLICATION_JSON_VALUE })
    public Survey getQuestionnaire(@PathVariable final String uuid) {
        log.info("Requesting survey with ID {}", uuid);
        return surveyService.get(uuid);
    }

}
