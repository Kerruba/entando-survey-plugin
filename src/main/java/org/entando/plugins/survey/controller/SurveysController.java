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
import org.entando.plugins.survey.model.SurveySubmission;
import org.entando.plugins.survey.service.SurveyService;
import org.entando.plugins.survey.service.SurveySubmissionPdfExportService;
import org.entando.web.request.PagedListRequest;
import org.entando.web.response.PagedRestResponse;
import org.entando.web.response.SimpleRestResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.entando.plugins.survey.controller.AuthPermissions.SURVEY_CREATE;
import static org.entando.plugins.survey.controller.AuthPermissions.SURVEY_GET;
import static org.entando.plugins.survey.controller.AuthPermissions.SURVEY_LIST;
import static org.entando.plugins.survey.controller.AuthPermissions.SURVEY_SUBMISSION_CREATE;
import static org.entando.plugins.survey.controller.AuthPermissions.SURVEY_SUBMISSION_EXPORT;
import static org.entando.plugins.survey.controller.AuthPermissions.SURVEY_SUBMISSION_GET;
import static org.entando.plugins.survey.controller.AuthPermissions.SURVEY_SUBMISSION_LIST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;

@Slf4j
@RestController
@Api(tags = "Surveys")
@RequestMapping(path = "/surveys")
@RequiredArgsConstructor
public class SurveysController {

    private final @NonNull SurveyService surveyService;
    private final @NonNull SurveySubmissionPdfExportService exportService;

    @Secured(SURVEY_GET)
    @ApiOperation(notes = "Gets a survey", nickname = "getSurvey", value = "GET Survey")
    @GetMapping(path = "/{uuid}", produces = { APPLICATION_JSON_VALUE })
    public SimpleRestResponse<SurveyDto> get(@PathVariable final String uuid) {
        log.info("Requesting survey with ID {}", uuid);
        return new SimpleRestResponse<>(SurveyDto.fromModel(surveyService.get(uuid)));
    }

    @Secured(SURVEY_CREATE)
    @ApiOperation(notes = "Creates a survey", nickname = "createSurvey", value = "CREATE Survey")
    @PostMapping(produces = { APPLICATION_JSON_VALUE })
    public SimpleRestResponse<SurveyDto> create(final @Valid @RequestBody CreateSurveyRequest request) {
        log.info("Creating survey {} with request {}", request, request);
        return new SimpleRestResponse<>(SurveyDto.fromModel(surveyService.create(request)));
    }

    @Secured(SURVEY_LIST)
    @ApiOperation(notes = "Lists all surveys", nickname = "listSurveus", value = "LIST Survey")
    @GetMapping(produces = { APPLICATION_JSON_VALUE })
    public PagedRestResponse<SurveyDto> list(final PagedListRequest restListRequest) {
        log.info("Listing surveys {}", restListRequest);
        return surveyService.list(restListRequest)
                .map(SurveyDto::fromModel)
                .toRestResponse();
    }

    @Secured(SURVEY_SUBMISSION_LIST)
    @ApiOperation(notes = "Lists a survey submissions", nickname = "listSurveySubmissions", value = "LIST SurveySubmission")
    @GetMapping(path = "/{uuid}/submissions", produces = { APPLICATION_JSON_VALUE })
    //TODO support query filters
    public PagedRestResponse<SurveySubmissionDto> listSubmissions(@PathVariable final String uuid, final PagedListRequest listRequest) {
        log.info("Listing survey submissions for survey ID {} and request {}", uuid, listRequest);
        return surveyService.listSubmissions(uuid, listRequest)
                .map(SurveySubmissionDto::fromModel)
                .toRestResponse();
    }

    @Secured(SURVEY_SUBMISSION_CREATE)
    @ApiOperation(notes = "Submits a survey", nickname = "createSurveySubmission", value = "CREATE SurveySubmission")
    @PostMapping(path = "/{uuid}/submissions", produces = { APPLICATION_JSON_VALUE })
    public SimpleRestResponse<SurveySubmissionDto> submitSurvey(
            @PathVariable final String uuid, @Valid @RequestBody final SubmitSurveyRequest request) {
        log.info("Submitting survey with ID {} and request {}", uuid, request);
        return new SimpleRestResponse<>(SurveySubmissionDto.fromModel(surveyService.submit(uuid, request)));
    }

    @Secured(SURVEY_SUBMISSION_GET)
    @ApiOperation(notes = "Gets a survey submission", nickname = "getSurveySubmission", value = "GET SurveySubmission")
    @GetMapping(path = "/{uuid}/submissions/{submissionUuid}", produces = { APPLICATION_JSON_VALUE })
    public SimpleRestResponse<SurveySubmissionDto> getSubmission(
            @PathVariable final String uuid, @PathVariable final String submissionUuid) {
        log.info("Requesting survey submission with ID {} and survey ID {}", submissionUuid, uuid);
        return new SimpleRestResponse<>(SurveySubmissionDto.fromModel(
                surveyService.getSubmission(uuid, submissionUuid)));
    }

    @Secured(SURVEY_SUBMISSION_EXPORT)
    @ApiOperation(notes = "Exports to PDF a list of survey submissions", nickname = "exportPdfSurveySubmissions", value = "EXPORT SurveySubmissions")
    @GetMapping(path = "/{uuid}/submissions/export", produces = { APPLICATION_PDF_VALUE })
    public ResponseEntity<InputStreamResource> exportSubmissions(
            @PathVariable final String uuid,
            final PagedListRequest listRequest,
            @RequestParam(name = "submissionIds", required = false) final String submissionIds) {

        final List<UUID> submissions = Optional.ofNullable(submissionIds).map(item ->
            Arrays.stream(item.split(",")).map(UUID::fromString).collect(Collectors.toList())
        ).orElse(Collections.emptyList());

        return createResponse(surveyService.listSubmissions(uuid, listRequest, submissions).getBody());
    }

    @Secured(SURVEY_SUBMISSION_EXPORT)
    @ApiOperation(notes = "Exports to PDF a survey submission", nickname = "exportPdfSurveySubmission", value = "EXPORT SurveySubmission")
    @GetMapping(path = "/{uuid}/submissions/{submissionUuid}/export", produces = { APPLICATION_PDF_VALUE })
    public ResponseEntity<InputStreamResource> exportSubmission(@PathVariable final String uuid, @PathVariable final String submissionUuid, final HttpServletResponse response) {
        final SurveySubmission submission = surveyService.getSubmission(uuid, submissionUuid);
        return createResponse(Collections.singletonList(submission));
    }

    private ResponseEntity<InputStreamResource> createResponse(final List<SurveySubmission> submissions) {
        try (final ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            exportService.createPdf(submissions, stream);
            final byte[] exported = stream.toByteArray();
            final HttpHeaders respHeaders = new HttpHeaders();
            respHeaders.setContentType(MediaType.APPLICATION_PDF);
            respHeaders.setContentLength(exported.length);
            respHeaders.setContentDispositionFormData("attachment", "survey_export.pdf");
            final InputStreamResource isr = new InputStreamResource(new ByteArrayInputStream(exported));
            return new ResponseEntity<>(isr, respHeaders, HttpStatus.OK);
        } catch (Exception e) {
            //TODO handle Exception
            log.error("Error generating PDF", e);
            throw new RuntimeException("Error generating PDF");
        }
    }

}
