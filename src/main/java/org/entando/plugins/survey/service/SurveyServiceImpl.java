package org.entando.plugins.survey.service;

import lombok.RequiredArgsConstructor;
import org.entando.plugins.survey.dto.request.CreateSurveyRequest;
import org.entando.plugins.survey.dto.request.SubmitSurveyRequest;
import org.entando.plugins.survey.exception.SurveyNotFoundException;
import org.entando.plugins.survey.exception.SurveySubmissionNotFoundException;
import org.entando.plugins.survey.model.Answer;
import org.entando.plugins.survey.model.BasePageable;
import org.entando.plugins.survey.model.Survey;
import org.entando.plugins.survey.model.SurveySubmission;
import org.entando.plugins.survey.model.answer.AnswerList;
import org.entando.plugins.survey.model.answer.AnswerRate;
import org.entando.plugins.survey.model.answer.AnswerText;
import org.entando.plugins.survey.repository.answer.AnswerListRepository;
import org.entando.plugins.survey.repository.answer.AnswerRateRepository;
import org.entando.plugins.survey.repository.answer.AnswerTextRepository;
import org.entando.plugins.survey.repository.survey.SurveyRepository;
import org.entando.plugins.survey.repository.survey.SurveySubmissionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private final SurveySubmissionRepository surveySubmissionRepository;
    private final SurveyRepository surveyRepository;
    private final AnswerListRepository answerListRepository;
    private final AnswerTextRepository answerTextRepository;
    private final AnswerRateRepository answerRateRepository;

    @Override
    public Survey get(final String uuid) {
        return surveyRepository.findById(UUID.fromString(uuid))
                .orElseThrow(SurveyNotFoundException::new);
    }

    @Override
    public Survey create(final CreateSurveyRequest request)  {
        return surveyRepository.save(request.getModel());
    }

    @Override
    public List<Survey> list(BasePageable page) {
        return surveyRepository.findAll(PageRequest.of(page.getPage(), page.getPageSize()))
                .getContent();
    }

    @Override
    public SurveySubmission submit(String uuid, SubmitSurveyRequest request) {
        Survey survey = surveyRepository.findById(UUID.fromString(uuid))
                .orElseThrow(SurveyNotFoundException::new);

        SurveySubmission submission = SurveySubmission.builder()
                .survey(survey)
                .submissionDate(new Date())
                .build();

        submission = surveySubmissionRepository.save(submission);

        List<Answer> answers = new ArrayList<>(); //TODO Ugly ass code
        for (Answer answer : request.getModel()) {
            answer.setSubmission(submission);
            if (answer instanceof AnswerRate) {
                answers.add(answerRateRepository.save((AnswerRate)answer));
            } else if (answer instanceof AnswerList) {
                answers.add(answerListRepository.save((AnswerList) answer));
            } else if (answer instanceof AnswerText) {
                answers.add(answerTextRepository.save((AnswerText) answer));
            } else throw new RuntimeException(); // TODO better exception?
        }

        submission.setAnswers(answers);
        return submission;
    }

    @Override
    public SurveySubmission getSubmission(String surveyUuid, String submissionUuid) {
        return surveySubmissionRepository.findByIdAndSurveyId(UUID.fromString(submissionUuid), UUID.fromString(surveyUuid))
                .orElseThrow(SurveySubmissionNotFoundException::new);
    }

    @Override
    public List<SurveySubmission> listSubmissions(String uuid, BasePageable page) {
        return surveySubmissionRepository.findAll(PageRequest.of(page.getPage(), page.getPageSize()))
                .getContent();
    }
}
