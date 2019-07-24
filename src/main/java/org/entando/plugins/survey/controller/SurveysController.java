package org.entando.plugins.survey.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.entando.plugins.survey.model.*;
import org.entando.plugins.survey.request.SubmitSurveyRequest;
import org.entando.plugins.survey.service.SurveyService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.entando.plugins.survey.controller.AuthPermissions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@Api(tags = "Surveys")
@RequestMapping(path = "/surveys")
@RequiredArgsConstructor
public class SurveysController {

    private final @NonNull SurveyService surveyService;

    @Secured(SURVEY_GET)
    @ApiOperation(notes = "Gets a survey", nickname = "getSurvey", value = "Get a Survey")
    @GetMapping(path = "/{uuid}", produces = { APPLICATION_JSON_VALUE })
    public Survey getQuestionnaire(@PathVariable final String uuid) {
        log.info("Requesting survey with ID {}", uuid);
        return surveyService.get(uuid);
    }

    @Secured(SURVEY_ANSWER_LIST)
    @ApiOperation(notes = "Lists a survey submissions", nickname = "listSurveySubmissions", value = "List Survey Submissions")
    @GetMapping(path = "/{uuid}/submissions", produces = { APPLICATION_JSON_VALUE })
    //TODO support query filters
    public BasePage<SurveySubmission> listSubmissions(@PathVariable final String uuid, final BasePageable page) {
        log.info("Lists survey submissions for survey ID {}", uuid);
        return surveyService.list(uuid, page);
    }

    @Secured(SURVEY_ANSWER_CREATE)
    @ApiOperation(notes = "Submits a survey", nickname = "submitSurvey", value = "Create a Survey Submission")
    @PostMapping(path = "/{uuid}/submissions", produces = { APPLICATION_JSON_VALUE })
    public SurveySubmission submitSurvey(@PathVariable final String uuid, @Valid @RequestBody final SubmitSurveyRequest request) {
        log.info("Submitting survey with ID {}", uuid);
        List<AnswerQuestion> answers = new ArrayList<>(); //TODO allow polimorphic answers
        answers.addAll(request.getListAnswers());
        answers.addAll(request.getTextAnswers());
        answers.addAll(request.getRateAnswers());
        return surveyService.submit(uuid, new Date(), answers);
    }

    @Secured(SURVEY_ANSWER_GET)
    @ApiOperation(notes = "Gets a survey submission", nickname = "submitSurvey", value = "Get a Survey Submission")
    @GetMapping(path = "/{uuid}/submissions/{submissionUuid}", produces = { APPLICATION_JSON_VALUE })
    public SurveySubmission submitSurvey(@PathVariable final String uuid, @PathVariable final String submissionUuid) {
        log.info("Requesting survey submission with ID {} and survey ID {}", submissionUuid, uuid);
        return surveyService.getSubmission(uuid, submissionUuid);
    }

}
