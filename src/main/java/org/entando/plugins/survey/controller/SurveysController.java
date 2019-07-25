package org.entando.plugins.survey.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.entando.plugins.survey.dto.request.CreateSurveyRequest;
import org.entando.plugins.survey.dto.request.SubmitSurveyRequest;
import org.entando.plugins.survey.dto.submission.SurveySubmissionDto;
import org.entando.plugins.survey.dto.survey.SurveyDto;
import org.entando.plugins.survey.model.BasePage;
import org.entando.plugins.survey.model.BasePageable;
import org.entando.plugins.survey.model.Survey;
import org.entando.plugins.survey.model.SurveySubmission;
import org.entando.plugins.survey.service.SurveyService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.entando.plugins.survey.controller.AuthPermissions.SURVEY_CREATE;
import static org.entando.plugins.survey.controller.AuthPermissions.SURVEY_GET;
import static org.entando.plugins.survey.controller.AuthPermissions.SURVEY_LIST;
import static org.entando.plugins.survey.controller.AuthPermissions.SURVEY_SUBMISSION_CREATE;
import static org.entando.plugins.survey.controller.AuthPermissions.SURVEY_SUBMISSION_GET;
import static org.entando.plugins.survey.controller.AuthPermissions.SURVEY_SUBMISSION_LIST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@Api(tags = "Surveys")
@RequestMapping(path = "/surveys")
@RequiredArgsConstructor
public class SurveysController {

    private final @NonNull SurveyService surveyService;

    @Secured(SURVEY_GET)
    @ApiOperation(notes = "Gets a survey", nickname = "getSurvey", value = "GET Survey")
    @GetMapping(path = "/{uuid}", produces = { APPLICATION_JSON_VALUE })
    public SurveyDto get(@PathVariable final String uuid) {
        log.info("Requesting survey with ID {}", uuid);
        return SurveyDto.fromModel(surveyService.get(uuid));
    }

    @Secured(SURVEY_CREATE)
    @ApiOperation(notes = "Creates a survey", nickname = "createSurvey", value = "CREATE Survey")
    @PostMapping(produces = { APPLICATION_JSON_VALUE })
    public SurveyDto create(final @Valid @RequestBody CreateSurveyRequest request) {
        log.info("Creating survey");
        return SurveyDto.fromModel(surveyService.create(request));
    }

    @Secured(SURVEY_LIST)
    @ApiOperation(notes = "Lists all surveys", nickname = "listSurveus", value = "LIST Survey")
    @GetMapping(produces = { APPLICATION_JSON_VALUE })
    public BasePage<SurveyDto> list(final BasePageable page) {
        log.info("Listing surveys");
        List<Survey> result = surveyService.list(page);
        List<SurveyDto> dtos = new ArrayList<>();
        for (Survey survey : result) {
            dtos.add(SurveyDto.fromModel(survey));
        }

        return BasePage.<SurveyDto>builder()
                .list(dtos)
                .page(page.getPage())
                .pageSize(page.getPageSize()) //TODO Possible bug on page count generation?
                .build();
    }

    @Secured(SURVEY_SUBMISSION_LIST)
    @ApiOperation(notes = "Lists a survey submissions", nickname = "listSurveySubmissions", value = "LIST SurveySubmission")
    @GetMapping(path = "/{uuid}/submissions", produces = { APPLICATION_JSON_VALUE })
    //TODO support query filters
    public BasePage<SurveySubmissionDto> listSubmissions(@PathVariable final String uuid, final BasePageable page) {
        log.info("Lists survey submissions for survey ID {}", uuid);


        List<SurveySubmission> result = surveyService.listSubmissions(uuid, page);
        List<SurveySubmissionDto> dtos = new ArrayList<>();
        for (SurveySubmission submission : result) {
            dtos.add(SurveySubmissionDto.fromModel(submission));
        }

        return BasePage.<SurveySubmissionDto>builder()
                .list(dtos)
                .page(page.getPage())
                .pageSize(page.getPageSize()) //TODO Possible bug on page count generation?
                .build();
    }

    @Secured(SURVEY_SUBMISSION_CREATE)
    @ApiOperation(notes = "Submits a survey", nickname = "createSurveySubmission", value = "CREATE SurveySubmission")
    @PostMapping(path = "/{uuid}/submissions", produces = { APPLICATION_JSON_VALUE })
    public SurveySubmissionDto submitSurvey(@PathVariable final String uuid, @Valid @RequestBody final SubmitSurveyRequest request) {
        log.info("Submitting survey with ID {}", uuid);
        return SurveySubmissionDto.fromModel(
                surveyService.submit(uuid, request));
    }

    @Secured(SURVEY_SUBMISSION_GET)
    @ApiOperation(notes = "Gets a survey submission", nickname = "getSurveySubmission", value = "GET SurveySubmission")
    @GetMapping(path = "/{uuid}/submissions/{submissionUuid}", produces = { APPLICATION_JSON_VALUE })
    public SurveySubmissionDto getSubmission(@PathVariable final String uuid, @PathVariable final String submissionUuid) {
        log.info("Requesting survey submission with ID {} and survey ID {}", submissionUuid, uuid);
        new SurveySubmissionDto();
        return SurveySubmissionDto.fromModel(
                surveyService.getSubmission(uuid, submissionUuid));
    }

}
